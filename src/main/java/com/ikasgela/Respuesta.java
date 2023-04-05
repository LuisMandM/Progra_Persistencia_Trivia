package com.ikasgela;

import java.util.List;

public class Respuesta {
    private String texto;
    private boolean correcta = false;
    private int pregunta_asociada ;

    public Respuesta(String texto, int correcta, int pregunta_asociada) {
        this.texto = texto;
        if (correcta==1) this.correcta = true;
        this.pregunta_asociada = pregunta_asociada;
    }

    public Respuesta() {
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public boolean isCorrecta() {
        return correcta;
    }

    public void setCorrecta(int correcta) {
        if (correcta==1) this.correcta = true;
    }

    public int getPregunta_asociada() {
        return pregunta_asociada;
    }

    public void setPregunta_asociada(int pregunta_asociada) {
        this.pregunta_asociada = pregunta_asociada;
    }

    @Override
    public String toString() {
        return "Respuesta{" +
                "texto='" + texto + '\'' +
                ", correcta=" + correcta +
                ", pregunta_asociada=" + pregunta_asociada +
                '}';
    }
}
