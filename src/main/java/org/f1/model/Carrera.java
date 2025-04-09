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
        /* TAREA: Reporte de puntos por constructor
         * @Responsable: Sebastian
         * @Descripción: Generar un Map donde la clave es el nombre del constructor
         * y el valor son los puntos obtenidos en la carrera por sus pilotos
         */
        return new HashMap<>();
    }

    @Override
    public String calcularDiferenciaConGanador(int posicion) {
        /* TAREA: Cálculo de diferencias de tiempo
         * @Responsable: Sebastian
         * @Descripción: Calcular la diferencia de tiempo entre el ganador
         * y el piloto en la posición especificada. Formato: "+XX.XXX"
         * Lanzar IllegalArgumentException si la posición no existe o es la primera
         */
        return "";
    }

    @Override
    public String obtenerInformacionCompleta() {
        /* TAREA: Formato de información de carrera
         * @Responsable: Sebastian
         * @Descripción: Generar string con formato:
         * "Circuito: Nombre - Fecha: DD/MM/YYYY HH:mm
         * Tipo: [Sprint/Carrera Principal]
         * Resultados:
         * 1. Piloto1 - Equipo1 - Tiempo1 - XXpts
         * 2. Piloto2 - Equipo2 - Tiempo2 - XXpts"
         */
        return "";
    }

    @Override
    public void generarReporte() {
        System.out.println("=== Reporte de Carrera ===");
        System.out.println(obtenerInformacionCompleta());
        System.out.println("\nPuntos por Constructor:");
        Map<String, Integer> puntosConstructores = generarReporteConstructores();
        puntosConstructores.forEach((constructor, puntos) -> 
            System.out.printf("%s: %d pts%n", constructor, puntos));
    }

    @Override
    public void exportarReporte(String formato) {
        // TODO: Implementar exportación a diferentes formatos (PDF, Excel, etc.)
        throw new UnsupportedOperationException("Exportación a " + formato + " aún no implementada");
    }

    @Override
    public String obtenerReporteResumido() {
        StringBuilder resumen = new StringBuilder();
        resumen.append(String.format("Carrera: %s%n", circuito.getNombre()));
        resumen.append(String.format("Fecha: %s%n", fecha.toString()));
        resumen.append(String.format("Tipo: %s%n", esSprint ? "Sprint" : "Carrera Principal"));
        resumen.append(String.format("Ganador: %s%n", resultados.get(0).getPiloto().getNombre()));
        return resumen.toString();
    }

    @Override
    public String obtenerInformacionBasica() {
        return String.format("%s - %s - %s",
            circuito.getNombre(),
            fecha.toString(),
            esSprint ? "Sprint" : "Carrera Principal");
    }

    @Override
    public String obtenerInformacionDetallada() {
        StringBuilder info = new StringBuilder();
        info.append(obtenerInformacionBasica()).append("\n\nResultados:\n");
        
        for (int i = 0; i < resultados.size(); i++) {
            ResultadoPiloto resultado = resultados.get(i);
            Piloto piloto = resultado.getPiloto();
            info.append(String.format("%d. %s - %s - %d pts%n",
                i + 1,
                piloto.getNombre(),
                piloto.getEquipo().getNombre(),
                resultado.getPuntos()));
        }
        
        return info.toString();
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

