package com.dam.evaluaciont1_drb.datos;

import android.os.Parcel;
import android.os.Parcelable;

public class Resultado implements Parcelable {

    // Declaramos los atributos de la clase Resultado.
    private String fase;
    private String fecha;
    private String equipo1;
    private int golesEquipo1;
    private String equipo2;
    private int golesEquipo2;

    // Constructor de la clase Resultado.
    public Resultado(String fase, String fecha, String equipo1, int golesEquipo1, String equipo2, int golesEquipo2) {
        this.fase = fase;
        this.fecha = fecha;
        this.equipo1 = equipo1;
        this.golesEquipo1 = golesEquipo1;
        this.equipo2 = equipo2;
        this.golesEquipo2 = golesEquipo2;
    }

    // Constructor de la clase Resultado para el Parcelable.
    protected Resultado(Parcel in) {
        this.fase = in.readString();
        this.fecha = in.readString();
        this.equipo1 = in.readString();
        this.golesEquipo1 = in.readInt();
        this.equipo2 = in.readString();
        this.golesEquipo2 = in.readInt();
    }

    // Generamos los getters y setters de los atributos de la clase Resultado.
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

    public void setEquipo2(String equipo2) {
        this.equipo2 = equipo2;
    }

    public int getGolesEquipo1() {
        return golesEquipo1;
    }

    public void setGolesEquipo1(int golesEquipo1) {
        this.golesEquipo1 = golesEquipo1;
    }

    public int getGolesEquipo2() {
        return golesEquipo2;
    }

    public void setGolesEquipo2(int golesEquipo2) {
        this.golesEquipo2 = golesEquipo2;
    }

    // Método para mostrar los resultados de los partidos. (Creado para hacer pruebas
    // en el logcat).
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

    // Este método es necesario para el Parcelable.
    // Es el que se encarga de crear el objeto a partir del Parcel.
    @Override
    public int describeContents() {
        return 0;
    }

    // Método para escribir los datos del objeto en el Parcel.
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.fase);
        dest.writeString(this.fecha);
        dest.writeString(this.equipo1);
        dest.writeInt(this.golesEquipo1);
        dest.writeString(this.equipo2);
        dest.writeInt(this.golesEquipo2);
    }

    // Método para crear el objeto a partir del Parcel. (Para el Parcelable).
    public static final Creator<Resultado> CREATOR = new Creator<Resultado>() {
        @Override
        public Resultado createFromParcel(Parcel in) {
            return new Resultado(in);
        }

        @Override
        public Resultado[] newArray(int size) {
            return new Resultado[size];
        }
    };
}
