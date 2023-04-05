package com.ikasgela;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pregunta {
    private int id;
    private String texto;

    //Asociacion
    private Respuesta[] respuestas = new Respuesta[4];

    public Pregunta() {
    }

    public Pregunta(int id, String texto) {
        this.id = id;
        this.texto = texto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Respuesta[] getRespuestas() {
        return respuestas;
    }

    @Override
    public String toString() {
        return "Pregunta{" +
                "id=" + id +
                ", texto='" + texto + '\'' +
                ", respuestas=" + Arrays.toString(respuestas) +
                '}';
    }
}
