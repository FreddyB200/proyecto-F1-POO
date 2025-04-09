package org.f1.model.interfaces;

import java.util.Map;

/**
 * Interfaz que define el comportamiento de elementos que pueden generar reportes
 * en el campeonato de F1 2024.
 */
public interface Reportable {
    /**
     * Genera un reporte de puntos por constructor
     * @return Map con el nombre del constructor como clave y los puntos como valor
     */
    Map<String, Integer> generarReporteConstructores();
    
    /**
     * Calcula la diferencia de tiempo con respecto al ganador
     * @param posicion la posición del piloto a comparar
     * @return String con el formato "+XX.XXX" representando la diferencia en segundos
     * @throws IllegalArgumentException si la posición no existe o es la primera posición
     */
    String calcularDiferenciaConGanador(int posicion);
} 