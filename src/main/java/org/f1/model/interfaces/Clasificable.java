package org.f1.model.interfaces;

public interface Clasificable {
    int obtenerPosicion();
    void actualizarPosicion(int nuevaPosicion);
    int calcularPuntos();
    boolean estaCalificado();
} 