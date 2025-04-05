package org.f1.model;

public class Equipo {
    private String nombre;
    private String nacionalidad;
    private String motorProveedor;
    private int puntosConstructores;

    public Equipo() {
    }

    public Equipo(String nombre, String nacionalidad, String motorProveedor) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.motorProveedor = motorProveedor;
        this.puntosConstructores = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getMotorProveedor() {
        return motorProveedor;
    }

    public void setMotorProveedor(String motorProveedor) {
        this.motorProveedor = motorProveedor;
    }

    public int getPuntosConstructores() {
        return puntosConstructores;
    }

    public void setPuntosConstructores(int puntosConstructores) {
        this.puntosConstructores = puntosConstructores;
    }

    public void sumarPuntos(int puntos) {
        this.puntosConstructores += puntos;
    }
} 