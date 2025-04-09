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
        // TODO: Implementar por Javier
        return false;
    }

    @Override
    public double calcularDistanciaTotal() {
        // TODO: Implementar por Javier
        return 0.0;
    }

    @Override
    public String obtenerInformacionCompleta() {
        // TODO: Implementar por Sebastian
        return "";
    }
} 