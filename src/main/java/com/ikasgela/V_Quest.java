package com.ikasgela;

import javax.swing.*;
import java.util.List;
import java.util.Random;

public class V_Quest {
    private JPanel panel1;
    private JButton d_ans_Button;
    private JButton a_ans_Button;
    private JButton c_ans_Button;
    private JButton b_ans_Button;
    private JTextArea textArea1;
    private JLabel record_Label;
    private final List<Pregunta> preguntas;
    private Pregunta current_Quest;
    JButton[] botones = {a_ans_Button, b_ans_Button, c_ans_Button, d_ans_Button};
    int current_record = 0;

    public V_Quest() {
        preguntas = Main.Preguntas();
        record_Label.setText("Record: " + current_record);
        SetQuest();
        a_ans_Button.addActionListener(e -> Validar_Respuesta(0));
        b_ans_Button.addActionListener(e -> Validar_Respuesta(1));
        c_ans_Button.addActionListener(e -> Validar_Respuesta(2));
        d_ans_Button.addActionListener(e -> Validar_Respuesta(3));
    }

    private void SetQuest() {
        Random rand = new Random();
        Pregunta randomQuest = preguntas.get(rand.nextInt(preguntas.size()));
        textArea1.setText(FormatQuest(randomQuest.getTexto()));
        for (int i = 0; i < randomQuest.getRespuestas().length; i++) {
            if (randomQuest.getRespuestas()[i] != null) botones[i].setText(randomQuest.getRespuestas()[i].getTexto());
        }
        current_Quest = randomQuest;
    }

    private String FormatQuest(String pregunta) {
        StringBuilder formato = new StringBuilder();

        char[] letras = pregunta.toCharArray();
        int count = 0;
        for (char letra : letras) {
            if (count < 50) {
                formato.append(letra);
                count++;
            } else if (letra != ' ') {
                formato.append(letra);
                count++;
            } else {
                formato.append("\n").append(letra);
                count = 0;
            }
        }
        return String.valueOf(formato);

    }

    private void Validar_Respuesta(int index_Opcion) {
        boolean valor = current_Quest.getRespuestas()[index_Opcion].isCorrecta();
        if (valor) current_record++;
        else {
            JOptionPane.showMessageDialog(null, "Game Over", "Game Over",
                    JOptionPane.INFORMATION_MESSAGE);
            Main.UpdateRecord(current_record);
            current_record = 0;
        }
        record_Label.setText("Record: " + current_record);
        SetQuest();
    }

    public JPanel getPanel() {
        return panel1;
    }
}
