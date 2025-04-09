package org.f1.model;

import java.util.HashMap;
import java.util.Map;

import org.f1.model.interfaces.Posicionable;
import org.f1.model.interfaces.Puntuable;

/**
 * Clase que representa un piloto de Fórmula 1 en la temporada 2024.
 * Extiende de la clase Persona y contiene información específica del piloto
 * como su equipo, puntos, número de coche y resultados en carreras.
 *
 * @author Equipo de Desarrollo F1
 * @version 1.0
 */
public class Piloto extends Persona implements Posicionable, Puntuable {
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

    @Override
    public int getPuntos() {
        return puntos2024;
    }

    @Override
    public void sumarPuntos(int puntos) {
        this.puntos2024 += puntos;
        if (this.equipo != null) {
            this.equipo.sumarPuntos(puntos);
        }
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
     * Actualiza los puntos del piloto basado en su posición en una carrera.
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

    @Override
    public int calcularPuntosPorPosicion(int posicion) {
        // TODO: Implementar por Javier
        return 0;
    }

    @Override
    public String obtenerInformacionCompleta() {
        // TODO: Implementar por Sebastian
        return "";
    }

    @Override
    public double obtenerPosicionPromedio() {
        // TODO: Implementar por Sebastian
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