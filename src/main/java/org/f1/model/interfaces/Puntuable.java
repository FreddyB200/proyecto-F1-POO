package org.f1.model.interfaces;

/**
 * Interfaz que define el comportamiento de elementos que pueden recibir puntos
 * en el campeonato de F1 2024.
 */
public interface Puntuable {
    /**
     * Obtiene los puntos actuales
     * @return int con los puntos acumulados
     */
    int getPuntos();
    
    /**
     * Suma puntos al total actual
     * @param puntos cantidad de puntos a sumar
     */
    void sumarPuntos(int puntos);
    
    /**
     * Calcula los puntos basado en una posición
     * @param posicion la posición final
     * @return los puntos correspondientes a esa posición
     */
    int calcularPuntosPorPosicion(int posicion);
} 