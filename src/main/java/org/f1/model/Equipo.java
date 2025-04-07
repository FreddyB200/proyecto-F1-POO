package org.f1.model;

import java.util.ArrayList;
import java.util.List;

public class Equipo {
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
    

    /**
     * TODO: Implementar por el equipo
     * Este método debe retornar un String con el formato:
     * "Nombre Equipo - Director - País - Campeonatos: X - Puntos 2024: XX"
     * @return String con la información formateada del equipo
     */
    public String obtenerInformacionCompleta() {
        // TODO: Implementar por el equipo
        return "";
    }

    /**
     * TODO: Implementar por el equipo
     * Este método debe retornar la posición promedio del equipo en la temporada actual
     * basándose en las posiciones de sus pilotos
     * @return double con la posición promedio
     */
    public double obtenerPosicionPromedio() {
        // TODO: Implementar por el equipo
        return 0.0;
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

    public void sumarPuntos(int puntos) {
        this.puntosConstructores2024 += puntos;
    }
} 