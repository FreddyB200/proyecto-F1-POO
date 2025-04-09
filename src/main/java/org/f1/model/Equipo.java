package org.f1.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.f1.model.interfaces.Informable;
import org.f1.model.interfaces.Posicionable;
import org.f1.model.interfaces.Puntuable;

public class Equipo implements Posicionable, Puntuable, Informable {
    private String nombre;
    private String director;
    private String pais;
    private int campeonatosGanados;
    private int puntosConstructores2024;
    private List<Piloto> pilotos;
    private String motorProveedor;
    private List<Integer> posicionesPorCarrera;

    public Equipo() {
        this.pilotos = new ArrayList<>();
        this.puntosConstructores2024 = 0;
        this.posicionesPorCarrera = new ArrayList<>();
    }

    public Equipo(String nombre, String director, String pais, String motorProveedor) {
        this.nombre = nombre;
        this.director = director;
        this.pais = pais;
        this.motorProveedor = motorProveedor;
        this.campeonatosGanados = 0;
        this.puntosConstructores2024 = 0;
        this.pilotos = new ArrayList<>();
        this.posicionesPorCarrera = new ArrayList<>();
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
        if (posicionesPorCarrera.isEmpty()) {
            return 0.0;
        }
        double suma = posicionesPorCarrera.stream()
                .mapToInt(Integer::intValue)
                .sum();
        return suma / posicionesPorCarrera.size();
    }

    @Override
    public String obtenerInformacionBasica() {
        return String.format("%s - %s", nombre, pais);
    }

    @Override
    public String obtenerInformacionDetallada() {
        StringBuilder info = new StringBuilder();
        info.append(String.format("%s\nDirector: %s\nMotor: %s\nPilotos:\n", 
            obtenerInformacionBasica(), director, motorProveedor));
        
        for (Piloto piloto : pilotos) {
            info.append(String.format("- %s %s (#%d)\n", 
                piloto.getNombre(), piloto.getApellido(), piloto.getNumero()));
        }
        
        info.append(String.format("\nPuntos 2024: %d\nCampeonatos: %d", 
            puntosConstructores2024, campeonatosGanados));
        
        return info.toString();
    }

    @Override
    public String obtenerInformacionCompleta() {
        return String.format("%s - Director: %s - Puntos 2024: %d - Campeonatos: %d",
            obtenerInformacionBasica(), director, puntosConstructores2024, campeonatosGanados);
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
        return switch (posicion) {
            case 1 -> 25;
            case 2 -> 18;
            case 3 -> 15;
            case 4 -> 12;
            case 5 -> 10;
            case 6 -> 8;
            case 7 -> 6;
            case 8 -> 4;
            case 9 -> 2;
            case 10 -> 1;
            default -> 0;
        };
    }

    @Override
    public int obtenerMejorPosicion() {
        if (posicionesPorCarrera.isEmpty()) {
            return 0;
        }
        return Collections.min(posicionesPorCarrera);
    }

    @Override
    public int obtenerPeorPosicion() {
        if (posicionesPorCarrera.isEmpty()) {
            return 0;
        }
        return Collections.max(posicionesPorCarrera);
    }

    @Override
    public int obtenerPuntosTotales() {
        return puntosConstructores2024;
    }

    @Override
    public void actualizarPuntos(int posicion, String nombreCarrera) {
        posicionesPorCarrera.add(posicion);
        int puntos = calcularPuntosPorPosicion(posicion);
        sumarPuntos(puntos);
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
        return new ArrayList<>(pilotos);
    }

    public String getMotorProveedor() {
        return motorProveedor;
    }

    public void setMotorProveedor(String motorProveedor) {
        this.motorProveedor = motorProveedor;
    }

    public List<Integer> getPosicionesPorCarrera() {
        return new ArrayList<>(posicionesPorCarrera);
    }

    public void setPosicionesPorCarrera(List<Integer> posicionesPorCarrera) {
        this.posicionesPorCarrera = new ArrayList<>(posicionesPorCarrera);
    }
} 