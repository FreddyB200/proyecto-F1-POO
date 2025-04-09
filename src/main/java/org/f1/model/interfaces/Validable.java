package org.f1.model.interfaces;

/**
 * Interfaz que define el comportamiento de elementos que requieren validación
 * en el campeonato de F1 2024.
 */
public interface Validable {
    /**
     * Valida las fechas de las carreras
     * @return true si las fechas son válidas, false en caso contrario
     */
    boolean validarFechas();
    
    /**
     * Calcula la distancia total de la carrera
     * @return double con la distancia total en kilómetros
     */
    double calcularDistanciaTotal();

    /**
     * Valida si el circuito cumple con los requisitos mínimos
     * @return true si cumple los requisitos, false en caso contrario
     */
    boolean validarRequisitosMinimos();
} 