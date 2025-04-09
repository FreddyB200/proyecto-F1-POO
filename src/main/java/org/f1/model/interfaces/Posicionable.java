package org.f1.model.interfaces;

/**
 * Interfaz que define el comportamiento de elementos que pueden tener posiciones y rankings
 * en el campeonato de F1 2024.
 */
public interface Posicionable {
    /**
     * Calcula la posición promedio en la temporada actual
     * @return double con la posición promedio
     */
    double obtenerPosicionPromedio();
    
    /**
     * Obtiene la información completa formateada del elemento
     * @return String con la información formateada
     */
    String obtenerInformacionCompleta();
} 