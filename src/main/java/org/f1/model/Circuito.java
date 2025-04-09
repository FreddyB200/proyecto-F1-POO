package org.f1.model;

import java.time.LocalDateTime;

import org.f1.model.interfaces.Informable;
import org.f1.model.interfaces.Validable;

public class Circuito implements Validable, Informable {
    private String nombre;
    private String pais;
    private double longitud; // en kilómetros
    private int numVueltas;
    private LocalDateTime fechaCarreraPrincipal;
    private LocalDateTime fechaCarreraSprint;

    public Circuito() {
    }

    public Circuito(String nombre, String pais, double longitud, int numVueltas,
                   LocalDateTime fechaCarreraPrincipal, LocalDateTime fechaCarreraSprint) {
        this.nombre = nombre;
        this.pais = pais;
        this.longitud = longitud;
        this.numVueltas = numVueltas;
        this.fechaCarreraPrincipal = fechaCarreraPrincipal;
        this.fechaCarreraSprint = fechaCarreraSprint;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public int getNumVueltas() {
        return numVueltas;
    }

    public void setNumVueltas(int numVueltas) {
        this.numVueltas = numVueltas;
    }

    public LocalDateTime getFechaCarreraPrincipal() {
        return fechaCarreraPrincipal;
    }

    public void setFechaCarreraPrincipal(LocalDateTime fechaCarreraPrincipal) {
        this.fechaCarreraPrincipal = fechaCarreraPrincipal;
    }

    public LocalDateTime getFechaCarreraSprint() {
        return fechaCarreraSprint;
    }

    public void setFechaCarreraSprint(LocalDateTime fechaCarreraSprint) {
        this.fechaCarreraSprint = fechaCarreraSprint;
    }

    @Override
    public boolean validarFechas() {
        /* TAREA: Validación de fechas de carreras
         * @Responsable: Javier
         * @Descripción: Validar que:
         * 1. La fecha del sprint sea anterior a la carrera principal
         * 2. Ambas fechas estén en el año 2024
         */
        return false;
    }

    @Override
    public double calcularDistanciaTotal() {
        /* TAREA: Cálculo de distancia total de carrera
         * @Responsable: Javier
         * @Descripción: Calcular la distancia total multiplicando
         * la longitud del circuito por el número de vueltas
         */
        return 0.0;
    }

    @Override
    public String obtenerInformacionCompleta() {
        /* TAREA: Formato de información de circuito
         * @Responsable: Sebastian
         * @Descripción: Generar string con formato:
         * "Nombre Circuito - País - Longitud: XX.XXX km - Vueltas: XX
         * Carrera Principal: DD/MM/YYYY HH:mm
         * Carrera Sprint: DD/MM/YYYY HH:mm"
         */
        return "";
    }

    @Override
    public boolean validarRequisitosMinimos() {
        // Validar longitud mínima (3.5 km)
        if (longitud < 3.5) {
            return false;
        }
        
        // Validar número de vueltas (mínimo 50)
        if (numVueltas < 50) {
            return false;
        }
        
        // Validar que tenga fechas asignadas
        if (fechaCarreraPrincipal == null || fechaCarreraSprint == null) {
            return false;
        }
        
        return true;
    }

    @Override
    public String obtenerInformacionBasica() {
        return String.format("%s - %s - %.3f km", nombre, pais, longitud);
    }

    @Override
    public String obtenerInformacionDetallada() {
        return String.format("%s\nVueltas: %d\nCarrera Principal: %s\nCarrera Sprint: %s",
            obtenerInformacionBasica(),
            numVueltas,
            fechaCarreraPrincipal.toString(),
            fechaCarreraSprint.toString());
    }
} 