package org.f1.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.f1.model.interfaces.Informable;
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
public class Piloto extends Persona implements Posicionable, Puntuable, Informable {
    private Equipo equipo;
    private int numero;
    private String codigo;
    private int puntos2024;
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
        this.puntos2024 = 0;
        this.campeonatosGanados = 0;
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
     * @param codigo Código del piloto
     */
    public Piloto(String nombre, String apellido, String nacionalidad, int edad,
                 Equipo equipo, int numero, String codigo) {
        super(nombre, apellido, nacionalidad, edad);
        this.equipo = equipo;
        this.numero = numero;
        this.codigo = codigo;
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
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Actualiza los puntos del piloto basado en su posición en una carrera.
     * También actualiza el mapa de posiciones y los puntos del equipo.
     *
     * @param posicion Posición final en la carrera
     * @param nombreCarrera Nombre del circuito donde se disputó la carrera
     */
    @Override
    public void actualizarPuntos(int posicion, String nombreCarrera) {
        posicionesPorCarrera.put(nombreCarrera, posicion);
        int puntos = calcularPuntosPorPosicion(posicion);
        sumarPuntos(puntos);
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
    public String obtenerInformacionBasica() {
        return String.format("%s %s - %s", nombre, apellido, equipo.getNombre());
    }

    @Override
    public String obtenerInformacionDetallada() {
        return String.format("%s\nEdad: %d\nNacionalidad: %s\nNúmero: %d\nCódigo: %s\nPuntos 2024: %d\nCampeonatos: %d",
            obtenerInformacionBasica(),
            edad,
            nacionalidad,
            numero,
            codigo,
            puntos2024,
            campeonatosGanados);
    }

    @Override
    public String obtenerInformacionCompleta() {
        return String.format("%s - Puntos 2024: %d - Campeonatos: %d",
            obtenerInformacionBasica(), puntos2024, campeonatosGanados);
    }

    @Override
    public double obtenerPosicionPromedio() {
        if (posicionesPorCarrera.isEmpty()) {
            return 0.0;
        }
        double suma = posicionesPorCarrera.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
        return suma / posicionesPorCarrera.size();
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
        return new HashMap<>(posicionesPorCarrera);
    }

    @Override
    public int obtenerMejorPosicion() {
        if (posicionesPorCarrera.isEmpty()) {
            return 0;
        }
        return Collections.min(posicionesPorCarrera.values());
    }

    @Override
    public int obtenerPeorPosicion() {
        if (posicionesPorCarrera.isEmpty()) {
            return 0;
        }
        return Collections.max(posicionesPorCarrera.values());
    }

    @Override
    public int obtenerPuntosTotales() {
        return puntos2024;
    }
} 