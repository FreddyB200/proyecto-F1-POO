package org.f1.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.f1.model.interfaces.Informable;
import org.f1.model.interfaces.Reportable;

public class Carrera implements Reportable, Informable {
    private Circuito circuito;
    private LocalDateTime fecha;
    private boolean esSprint;
    private List<ResultadoPiloto> resultados;

    public Carrera() {
        this.resultados = new ArrayList<>();
    }


    public Carrera(Circuito circuito, LocalDateTime fecha, boolean esSprint) {
        this.circuito = circuito;
        this.fecha = fecha;
        this.esSprint = esSprint;
        this.resultados = new ArrayList<>();
    }

    public static class ResultadoPiloto {
        private Piloto piloto;
        private int posicion;
        private String tiempo;
        private int puntos;

        public ResultadoPiloto(Piloto piloto, int posicion, String tiempo, int puntos) {
            this.piloto = piloto;
            this.posicion = posicion;
            this.tiempo = tiempo;
            this.puntos = puntos;
        }

        public Piloto getPiloto() {
            return piloto;
        }

        public void setPiloto(Piloto piloto) {
            this.piloto = piloto;
        }

        public int getPosicion() {
            return posicion;
        }

        public void setPosicion(int posicion) {
            this.posicion = posicion;
        }

        public String getTiempo() {
            return tiempo;
        }

        public void setTiempo(String tiempo) {
            this.tiempo = tiempo;
        }

        public int getPuntos() {
            return puntos;
        }

        public void setPuntos(int puntos) {
            this.puntos = puntos;
        }
    }

    public void agregarResultado(Piloto piloto, int posicion, String tiempo, int puntos) {
        ResultadoPiloto resultado = new ResultadoPiloto(piloto, posicion, tiempo, puntos);
        resultados.add(resultado);
        piloto.actualizarPuntos(posicion, circuito.getNombre());
    }

    @Override
    public Map<String, Integer> generarReporteConstructores() {
        // TODO: Implementar por Sebastian
        return new HashMap<>();
    }

    @Override
    public String calcularDiferenciaConGanador(int posicion) {
        // TODO: Implementar por Sebastian
        return "";
    }

    @Override
    public String obtenerInformacionCompleta() {
        // TODO: Implementar por Sebastian
        return "";
    }

    public Circuito getCircuito() {
        return circuito;
    }

    public void setCircuito(Circuito circuito) {
        this.circuito = circuito;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public boolean isEsSprint() {
        return esSprint;
    }

    public void setEsSprint(boolean esSprint) {
        this.esSprint = esSprint;
    }

    public List<ResultadoPiloto> getResultados() {
        return resultados;
    }

    public void setResultados(List<ResultadoPiloto> resultados) {
        this.resultados = resultados;
    }
} 

