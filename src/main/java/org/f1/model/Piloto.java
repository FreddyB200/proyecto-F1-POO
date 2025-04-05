package org.f1.model;

public class Piloto extends Persona {
    private Equipo equipo;
    private int puntos;
    private int numero;
    private String abreviatura;

    public Piloto() {
        super();
    }

    public Piloto(String nombre, String apellido, String nacionalidad, int edad, 
                 Equipo equipo, int numero, String abreviatura) {
        super(nombre, apellido, nacionalidad, edad);
        this.equipo = equipo;
        this.numero = numero;
        this.abreviatura = abreviatura;
        this.puntos = 0;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public void sumarPuntos(int puntos) {
        this.puntos += puntos;
        if (this.equipo != null) {
            this.equipo.sumarPuntos(puntos);
        }
    }
} 