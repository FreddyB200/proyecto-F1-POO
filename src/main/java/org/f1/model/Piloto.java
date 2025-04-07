package org.f1.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase que representa un piloto de Fórmula 1 en la temporada 2024.
 * Extiende de la clase Persona y contiene información específica del piloto
 * como su equipo, puntos, número de coche y resultados en carreras.
 *
 * @author Equipo de Desarrollo F1
 * @version 1.0
 */
public class Piloto extends Persona {
    private Equipo equipo;
    private int puntos2024;
    private int numero;
    private String abreviatura;
    private int campeonatosGanados;
    private int carrerasDisputadas;
    private Map<String, Integer> posicionesPorCarrera;

    /**
     * Constructor por defecto.
     * Inicializa un nuevo piloto con un mapa vacío de posiciones por carrera.
     */
    public Piloto() {
        super();
        this.posicionesPorCarrera = new HashMap<>();
    }

    /**
     * Constructor completo para crear un nuevo piloto.
     *
     * @param nombre Nombre del piloto
     * @param apellido Apellido del piloto
     * @param nacionalidad Nacionalidad del piloto
     * @param edad Edad del piloto
     * @param equipo Equipo al que pertenece
     * @param numero Número del coche
     * @param abreviatura Abreviatura de tres letras del piloto
     */
    public Piloto(String nombre, String apellido, String nacionalidad, int edad, 
                 Equipo equipo, int numero, String abreviatura) {
        super(nombre, apellido, nacionalidad, edad);
        this.equipo = equipo;
        this.numero = numero;
        this.abreviatura = abreviatura;
        this.puntos2024 = 0;
        this.campeonatosGanados = 0;
        this.carrerasDisputadas = 0;
        this.posicionesPorCarrera = new HashMap<>();
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public int getPuntos() {
        return puntos2024;
    }

    public void setPuntos(int puntos) {
        this.puntos2024 = puntos;
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

    /**
     * Update de  los puntos del piloto basado en su posición en una carrera.
     * También actualiza el mapa de posiciones y los puntos del equipo.
     *
     * @param posicion Posición final en la carrera
     * @param nombreCarrera Nombre del circuito donde se disputó la carrera
     */
    public void actualizarPuntos(int posicion, String nombreCarrera) {
        int puntosGanados = calcularPuntosPorPosicion(posicion);
        this.puntos2024 += puntosGanados;
        this.posicionesPorCarrera.put(nombreCarrera, posicion);
        if (this.equipo != null) {
            this.equipo.sumarPuntos(puntosGanados);
        }
        this.carrerasDisputadas++;
    }

    /**
     * TODO: Implementar por el equipo
     * Este método debe calcular los puntos basados en la posición según el sistema de puntos de F1 2024:
     * 1° = 25pts, 2° = 18pts, 3° = 15pts, etc.
     * No olvidar el punto extra por vuelta rápida si aplica
     * @param posicion la posición final del piloto en la carrera
     * @return los puntos correspondientes a esa posición
     */
    public int calcularPuntosPorPosicion(int posicion) {
        // TODO: Implementar por el equipo
        return 0;
    }

    /**
     * TODO: Implementar por el equipo
     * Este método debe retornar un String con el formato:
     * "Nombre Apellido - Equipo - Puntos 2024: XX - Campeonatos: X"
     * @return String con la información formateada del piloto
     */
    public String obtenerInformacionCompleta() {
        // TODO: Implementar por el equipo
        return "";
    }

    /**
     * TODO: Implementar por el equipo
     * Este método debe retornar la posición promedio del piloto en la temporada actual
     * @return double con la posición promedio
     */
    public double obtenerPosicionPromedio() {
        // TODO: Implementar por el equipo
        return 0.0;
    }

    // Getters y Setters adicionales
    public int getCampeonatosGanados() {
        return campeonatosGanados;
    }

    public void setCampeonatosGanados(int campeonatosGanados) {
        this.campeonatosGanados = campeonatosGanados;
    }

    public int getCarrerasDisputadas() {
        return carrerasDisputadas;
    }

    public Map<String, Integer> getPosicionesPorCarrera() {
        return posicionesPorCarrera;
    }
} 