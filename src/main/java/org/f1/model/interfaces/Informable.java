package org.f1.model.interfaces;

/**
 * Interfaz que define el comportamiento de elementos que pueden proporcionar información formateada
 * en el campeonato de F1 2024.
 */
public interface Informable {
    /**
     * Obtiene la información completa formateada del elemento
     * @return String con la información formateada
     */
    String obtenerInformacionCompleta();

    String obtenerInformacionBasica();

    String obtenerInformacionDetallada();
} 