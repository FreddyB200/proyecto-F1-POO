package org.f1.model.interfaces;

/**
 * Interfaz que define el comportamiento de elementos que requieren validación
 * en el campeonato de F1 2024.
 */
public interface Validable {
    /**
     * Valida las fechas del elemento
     * @return true si las fechas son válidas, false en caso contrario
     */
    boolean validarFechas();
    
    /**
     * Calcula la distancia total del elemento
     * @return double con la distancia total en kilómetros
     */
    double calcularDistanciaTotal();
} 