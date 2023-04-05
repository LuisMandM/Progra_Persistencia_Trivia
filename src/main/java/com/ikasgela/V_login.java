package com.ikasgela;

import javax.swing.*;

public class V_login {
    private JTextField players_nametextField;
    private JButton init_Button;
    private JPanel panel;

    public V_login(JFrame frame) {

        init_Button.addActionListener(e -> {
            String jugador = players_nametextField.getText().replaceAll(" ", "_");
            Jugador current_player = null;
            for (Jugador jugador_register : Main.Jugadores()) {
                if (jugador_register.getNombre().equals(jugador)) current_player = jugador_register;
            }
            if (current_player != null) Main.setCurrent_Player(current_player);
            else {
                Main.AddPlayer(jugador);
                current_player = new Jugador(jugador, 0);
                Main.setCurrent_Player(current_player);
                Main.playing = true;
            }
            frame.setVisible(false);
            Main.SetQuest();
            System.out.println("JUEGO INICIADO JUGADOR: " + current_player.getNombre());
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}
