package org.f1.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        Map<String, Integer> puntosConstructores = new HashMap<>();
        
        for (ResultadoPiloto resultado : resultados) {
            String nombreEquipo = resultado.getPiloto().getEquipo().getNombre();
            int puntos = resultado.getPuntos();
            
            puntosConstructores.merge(nombreEquipo, puntos, Integer::sum);
        }
        
        return puntosConstructores;
    }

    @Override
    public String calcularDiferenciaConGanador(int posicion) {
        if (posicion <= 1 || posicion > resultados.size()) {
            throw new IllegalArgumentException("Posición inválida para calcular diferencia");
        }

        ResultadoPiloto ganador = resultados.get(0);
        ResultadoPiloto piloto = resultados.get(posicion - 1);

        if (ganador.getTiempo().equals("DNF") || piloto.getTiempo().equals("DNF")) {
            return "DNF";
        }

        // Convertir tiempos de formato "1:30.500" a milisegundos
        long tiempoGanador = convertirTiempoAMilisegundos(ganador.getTiempo());
        long tiempoPiloto = convertirTiempoAMilisegundos(piloto.getTiempo());

        // Calcular diferencia y formatear
        long diferencia = tiempoPiloto - tiempoGanador;
        return String.format("+%.3f", diferencia / 1000.0);
    }

    private long convertirTiempoAMilisegundos(String tiempo) {
        String[] partes = tiempo.split("[:.]");
        long minutos = 0;
        long segundos = 0;
        long milisegundos = 0;

        if (partes.length == 3) {
            // Formato "m:ss.SSS"
            minutos = Long.parseLong(partes[0]);
            segundos = Long.parseLong(partes[1]);
            milisegundos = Long.parseLong(partes[2]);
        } else if (partes.length == 2) {
            // Formato "ss.SSS"
            segundos = Long.parseLong(partes[0]);
            milisegundos = Long.parseLong(partes[1]);
        }

        return (minutos * 60 * 1000) + (segundos * 1000) + milisegundos;
    }

    @Override
    public String obtenerInformacionCompleta() {
        StringBuilder info = new StringBuilder();
        info.append(String.format("Circuito: %s - Fecha: %s\n", 
            circuito.getNombre(), 
            fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))));
        info.append(String.format("Tipo: %s\n", esSprint ? "Sprint" : "Carrera Principal"));
        info.append("Resultados:\n");
        
        for (int i = 0; i < resultados.size(); i++) {
            ResultadoPiloto resultado = resultados.get(i);
            info.append(String.format("%d. %s - %s - %s - %d pts\n",
                i + 1,
                resultado.getPiloto().obtenerInformacionBasica(),
                resultado.getPiloto().getEquipo().getNombre(),
                resultado.getTiempo(),
                resultado.getPuntos()));
        }
        
        return info.toString();
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
            info.append(String.format("%d. %s - %s - %d pts%n",
                i + 1,
                resultado.getPiloto().obtenerInformacionBasica(),
                resultado.getPiloto().getEquipo().getNombre(),
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

