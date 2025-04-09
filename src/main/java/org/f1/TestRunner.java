package org.f1;

import org.f1.model.Equipo;
import org.f1.model.Piloto;

public class TestRunner {
    public static void main(String[] args) {
        System.out.println("Ejecutando pruebas manuales...\n");

        // Crear objetos de prueba
        Equipo redbull = new Equipo("Red Bull Racing", "Christian Horner", "Reino Unido", "Honda RBPT");
        Piloto verstappen = new Piloto("Max", "Verstappen", "Holandés", 26, redbull, 1, "VER");

        // Prueba 1: Calcular puntos por posición
        System.out.println("Prueba 1: Calcular puntos por posición");
        int puntosPrimerLugar = verstappen.calcularPuntosPorPosicion(1);
        System.out.printf("Puntos por primer lugar: %d (Esperado: 25)%n", puntosPrimerLugar);
        assert puntosPrimerLugar == 25 : "Error: Puntos incorrectos para el primer lugar";

        // Prueba 2: Actualizar puntos y posición promedio
        System.out.println("\nPrueba 2: Actualizar puntos y posición promedio");
        verstappen.actualizarPuntos(1, "Bahrein");
        verstappen.actualizarPuntos(3, "Arabia Saudita");
        System.out.printf("Posición promedio: %.1f (Esperado: 2.0)%n", verstappen.obtenerPosicionPromedio());
        assert verstappen.obtenerPosicionPromedio() == 2.0 : "Error: Promedio incorrecto";

        // Prueba 3: Información completa
        System.out.println("\nPrueba 3: Información completa");
        verstappen.setCampeonatosGanados(3);
        String info = verstappen.obtenerInformacionCompleta();
        System.out.println("Información: " + info);
        String esperado = "Max Verstappen - Red Bull Racing - Puntos 2024: 40 - Campeonatos: 3";
        assert info.equals(esperado) : "Error: Formato de información incorrecto";

        System.out.println("\n¡Todas las pruebas pasaron exitosamente!");
    }
} 