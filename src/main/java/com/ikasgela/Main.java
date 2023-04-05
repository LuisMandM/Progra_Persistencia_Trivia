package com.ikasgela;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final List<Pregunta> preguntas = new ArrayList<>();
    private static List<Jugador> jugadores = new ArrayList<>();
    private static Jugador current_Player;

    public static boolean playing = false;

    public static void main(String[] args) {
        ConsultaBD();
        LoginPlayer();
    }

    public static void SetQuest() {
        JFrame frame_Init = new JFrame("Pregunta");
        frame_Init.setContentPane(new V_Quest().getPanel());
        frame_Init.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame_Init.pack();
        frame_Init.setVisible(true);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = (screenSize.width - frame_Init.getWidth()) / 2;
        int y = (screenSize.height - frame_Init.getHeight()) / 2;
        frame_Init.setLocation(x, y);
    }

    private static void LoginPlayer() {
        JFrame frame_Init = new JFrame("Inicio");
        frame_Init.setContentPane(new V_login(frame_Init).getPanel());
        frame_Init.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame_Init.pack();
        frame_Init.setVisible(true);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = (screenSize.width - frame_Init.getWidth()) / 2;
        int y = (screenSize.height - frame_Init.getHeight()) / 2;
        frame_Init.setLocation(x, y);
    }

    public static void UpdateRecord(int record_actual) {
        if (record_actual > current_Player.getRecord()) {
            //Conexion
            String cadena_Conexion = "jdbc:sqlite:trivia_BD.sqlite";
            try {
                Connection conexion = DriverManager.getConnection(cadena_Conexion);
                String sql_Peticion = "UPDATE Jugadores SET record = ? WHERE nombre = ?";

                PreparedStatement pst = conexion.prepareStatement(sql_Peticion);
                pst.setInt(1, record_actual);
                pst.setString(2, current_Player.getNombre());
                int filas_modificadas = pst.executeUpdate();
                if (filas_modificadas > 0) System.out.println("Record Actualizado");
                else System.out.println("Ha ocurrido un error intente nuevamente");
                conexion.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void AddPlayer(String jugador) {
        //Conexion
        String cadena_Conexion = "jdbc:sqlite:trivia_BD.sqlite";
        try {
            Connection conexion = DriverManager.getConnection(cadena_Conexion);
            String sql_Peticion = "INSERT INTO Jugadores(nombre,record) VALUES(?,?)";

            PreparedStatement pst = conexion.prepareStatement(sql_Peticion);
            pst.setString(1, jugador);
            pst.setInt(2, 0);

            int filas_modificadas = pst.executeUpdate();
            if (filas_modificadas > 0) System.out.println("JUgador added");
            else System.out.println("Ha ocurrido un eror intente nuevamente");

            conexion.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void ConsultaBD() {
        //Conexion
        String cadena_Conexion = "jdbc:sqlite:trivia_BD.sqlite";

        try {
            Connection conexion = DriverManager.getConnection(cadena_Conexion);

            //Consulta preguntas
            String preguntas_query = "SELECT * FROM Preguntas";
            Statement preguntas_st = conexion.createStatement();
            ResultSet preguntas_rs = preguntas_st.executeQuery(preguntas_query);

            while (preguntas_rs.next()) {

                Pregunta pregunta = new Pregunta(preguntas_rs.getInt("cod_pregunta"),
                        preguntas_rs.getString("texto_pregunta"));

                String respuestas_query = "SELECT * FROM Respuestas WHERE pregunta = " + pregunta.getId();
                Statement respuestas_st = conexion.createStatement();
                ResultSet respuestas_rs = respuestas_st.executeQuery(respuestas_query);

                int index_ans = 0;
                while (respuestas_rs.next()) {
                    Respuesta respuesta_act = new Respuesta(respuestas_rs.getString("texto"),
                            respuestas_rs.getInt("correcta"), pregunta.getId());
                    pregunta.getRespuestas()[index_ans] = respuesta_act;
                    index_ans++;
                }
                preguntas.add(pregunta);
            }

            String players_query = "SELECT * FROM Jugadores";
            Statement players_st = conexion.createStatement();
            ResultSet players_rs = players_st.executeQuery(players_query);

            while (players_rs.next()) {
                Jugador jugador = new Jugador(players_rs.getString("nombre"),
                        players_rs.getInt("record"));
                jugadores.add(jugador);
            }

            conexion.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Pregunta> Preguntas() {
        return preguntas;
    }

    public static List<Jugador> Jugadores() {
        return jugadores;
    }

    public static void setCurrent_Player(Jugador current_Player) {
        Main.current_Player = current_Player;
        System.out.println(current_Player);
    }
}
