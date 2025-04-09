package org.f1.ui;

import java.util.Map;
import java.util.Scanner;

import org.f1.data.DatosPrecargados;
import org.f1.model.Piloto;

/**
 * Interfaz de usuario principal para el sistema de F1.
 * Los datos son precargados desde archivos JSON que fueron inicialmente
 * poblados usando la API de Ergast (ver documentación del proyecto).
 */
public class MainUI {
    private final DatosPrecargados datos;
    private final Scanner scanner;

    public MainUI() {
        this.datos = DatosPrecargados.getInstance();
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        while (true) {
            System.out.println("\n=== F1 Stats 2024 ===");
            System.out.println("1. Mostrar lista de pilotos");
            System.out.println("2. Ver clasificación de pilotos");
            System.out.println("3. Ver clasificación de constructores");
            System.out.println("4. Ver calendario de carreras");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    mostrarPilotos();
                    break;
                case 2:
                    mostrarClasificacionPilotos();
                    break;
                case 3:
                    mostrarClasificacionConstructores();
                    break;
                case 4:
                    mostrarCalendario();
                    break;
                case 5:
                    System.out.println("¡Hasta luego!");
                    return;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    private void mostrarPilotos() {
        System.out.println("\nPilotos de la temporada 2024:");
        for (Piloto piloto : datos.getPilotos()) {
            System.out.printf("%s %s - %s (#%d)%n",
                piloto.getNombre(),
                piloto.getApellido(),
                piloto.getEquipo().getNombre(),
                piloto.getNumero());
        }
    }

    private void mostrarClasificacionPilotos() {
        System.out.println("\nClasificación de Pilotos:");
        Map<String, Integer> clasificacion = datos.obtenerClasificacionPilotos();
        clasificacion.entrySet().stream()
            .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
            .forEach(e -> System.out.printf("%s - %d puntos%n",
                e.getKey(), e.getValue()));
    }

    private void mostrarClasificacionConstructores() {
        System.out.println("\nClasificación de Constructores:");
        Map<String, Integer> clasificacion = datos.obtenerClasificacionConstructores();
        clasificacion.entrySet().stream()
            .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
            .forEach(e -> System.out.printf("%s - %d puntos%n",
                e.getKey(), e.getValue()));
    }

    private void mostrarCalendario() {
        System.out.println("\nCalendario de carreras 2024:");
        datos.getCircuitos().forEach(circuito -> 
            System.out.printf("%s - %s%n",
                circuito.getNombre(),
                circuito.getFechaCarreraPrincipal().format(
                    java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
                )));
    }

    public static void main(String[] args) {
        MainUI ui = new MainUI();
        ui.mostrarMenu();
    }
} 