package org.f1.model.interfaces;

/**
 * Interfaz que define el comportamiento de elementos que pueden tener una posición
 * en el campeonato de F1 2024.
 */
public interface Posicionable {
    /**
     * Obtiene la posición promedio en el campeonato
     * @return double con el promedio de posiciones
     */
    double obtenerPosicionPromedio();

    /**
     * Actualiza los puntos basado en una posición en una carrera específica
     * @param posicion la posición obtenida
     * @param nombreCarrera el nombre de la carrera
     */
    void actualizarPuntos(int posicion, String nombreCarrera);

    /**
     * Obtiene la mejor posición alcanzada
     * @return int con la mejor posición
     */
    int obtenerMejorPosicion();

    /**
     * Obtiene la peor posición alcanzada
     * @return int con la peor posición
     */
    int obtenerPeorPosicion();
} 