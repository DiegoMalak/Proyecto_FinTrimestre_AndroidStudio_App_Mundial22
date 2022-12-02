package com.dam.evaluaciont1_drb.datos;

public class Resultado {

    private String fase;
    private String fecha;
    private String equipo1;
    private int golesEquipo1;
    private String equipo2;
    private int golesEquipo2;

    public Resultado(String fase, String fecha, String equipo1, int golesEquipo1, String equipo2, int golesEquipo2) {
        this.fase = fase;
        this.fecha = fecha;
        this.equipo1 = equipo1;
        this.golesEquipo1 = golesEquipo1;
        this.equipo2 = equipo2;
        this.golesEquipo2 = golesEquipo2;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEquipo1() {
        return equipo1;
    }

    public void setEquipo1(String equipo1) {
        this.equipo1 = equipo1;
    }

    public String getEquipo2() {
        return equipo2;
    }

    public int getGolesEquipo1() {
        return golesEquipo1;
    }

    public void setGolesEquipo1(int golesEquipo1) {
        this.golesEquipo1 = golesEquipo1;
    }

    public void setEquipo2(String equipo2) {
        this.equipo2 = equipo2;
    }

    public int getGolesEquipo2() {
        return golesEquipo2;
    }

    public void setGolesEquipo2(int golesEquipo2) {
        this.golesEquipo2 = golesEquipo2;
    }

    @Override
    public String toString() {
        return "Resultado{" +
                "fase='" + fase + '\'' +
                ", fecha='" + fecha + '\'' +
                ", equipo1='" + equipo1 + '\'' +
                ", golesEquipo1=" + golesEquipo1 +
                ", equipo2='" + equipo2 + '\'' +
                ", golesEquipo2=" + golesEquipo2 +
                '}';
    }
}
