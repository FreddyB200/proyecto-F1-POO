package org.f1.model.interfaces;

/**
 * Interfaz que define el comportamiento de elementos que pueden acumular puntos
 * en el campeonato de F1 2024.
 */
public interface Puntuable {
    /**
     * Calcula los puntos correspondientes a una posición específica
     * @param posicion la posición obtenida
     * @return puntos correspondientes a esa posición
     */
    int calcularPuntosPorPosicion(int posicion);

    /**
     * Suma puntos al total acumulado
     * @param puntos cantidad de puntos a sumar
     */
    void sumarPuntos(int puntos);

    /**
     * Obtiene el total de puntos acumulados
     * @return total de puntos
     */
    int obtenerPuntosTotales();
} 