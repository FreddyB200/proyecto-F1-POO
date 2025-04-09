package org.f1.model;

import java.util.ArrayList;
import java.util.List;

import org.f1.model.interfaces.Posicionable;
import org.f1.model.interfaces.Puntuable;

public class Equipo implements Posicionable, Puntuable {
    private String nombre;
    private String director;
    private String pais;
    private int campeonatosGanados;
    private int puntosConstructores2024;
    private List<Piloto> pilotos;
    private String motorProveedor;

    public Equipo() {
        this.pilotos = new ArrayList<>();
        this.puntosConstructores2024 = 0;
    }

    public Equipo(String nombre, String director, String pais, String motorProveedor) {
        this.nombre = nombre;
        this.director = director;
        this.pais = pais;
        this.motorProveedor = motorProveedor;
        this.campeonatosGanados = 0;
        this.puntosConstructores2024 = 0;
        this.pilotos = new ArrayList<>();
    }

    public void agregarPiloto(Piloto piloto) {
        if (pilotos.size() < 2) {
            pilotos.add(piloto);
            piloto.setEquipo(this);
        } else {
            throw new IllegalStateException("Un equipo no puede tener más de 2 pilotos titulares");
        }
    }

    public int calcularPuntos() {
        int totalPuntos = 0;
        for (Piloto piloto : pilotos) {
            totalPuntos += piloto.getPuntos();
        }
        return totalPuntos;
    }
    

    @Override
    public double obtenerPosicionPromedio() {
        // TODO: Implementar por Sebastian
        return 0.0;
    }

    @Override
    public String obtenerInformacionCompleta() {
        // TODO: Implementar por Sebastian
        return "";
    }

    @Override
    public int getPuntos() {
        return puntosConstructores2024;
    }

    @Override
    public void sumarPuntos(int puntos) {
        this.puntosConstructores2024 += puntos;
    }

    @Override
    public int calcularPuntosPorPosicion(int posicion) {
        // TODO: Implementar por Javier
        return 0;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getCampeonatosGanados() {
        return campeonatosGanados;
    }

    public void setCampeonatosGanados(int campeonatosGanados) {
        this.campeonatosGanados = campeonatosGanados;
    }

    public int getPuntosConstructores2024() {
        return puntosConstructores2024;
    }

    public List<Piloto> getPilotos() {
        return pilotos;
    }

    public String getMotorProveedor() {
        return motorProveedor;
    }

    public void setMotorProveedor(String motorProveedor) {
        this.motorProveedor = motorProveedor;
    }
} 