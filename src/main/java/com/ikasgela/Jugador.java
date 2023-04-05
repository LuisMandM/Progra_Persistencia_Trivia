package com.ikasgela;

public class Jugador {
    private String nombre;
    private int record;

    public Jugador() {
    }

    public Jugador(String nombre, int record) {
        this.nombre = nombre;
        this.record = record;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getRecord() {
        return record;
    }

    public void setRecord(int record) {
        this.record = record;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "nombre='" + nombre + '\'' +
                ", record=" + record +
                '}';
    }
}
