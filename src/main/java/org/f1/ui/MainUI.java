package org.f1.ui;

import java.time.format.DateTimeFormatter;
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
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

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
            System.out.println("5. Ver estadísticas detalladas de piloto");
            System.out.println("6. Ver resultados por carrera");
            System.out.println("7. Ver próxima carrera");
            System.out.println("8. Ver información de equipo");
            System.out.println("9. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1 -> mostrarPilotos();
                case 2 -> mostrarClasificacionPilotos();
                case 3 -> mostrarClasificacionConstructores();
                case 4 -> mostrarCalendario();
                case 5 -> mostrarEstadisticasPiloto();
                case 6 -> mostrarResultadosCarrera();
                case 7 -> mostrarProximaCarrera();
                case 8 -> mostrarInformacionEquipo();
                case 9 -> {
                    System.out.println("¡Hasta luego!");
                    return;
                }
                default -> System.out.println("Opción no válida");
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
                circuito.getFechaCarreraPrincipal().format(FORMATO_FECHA)));
    }

    private void mostrarEstadisticasPiloto() {
        System.out.println("\nIngrese el número del piloto:");
        int numero = scanner.nextInt();
        Piloto piloto = datos.getPilotoPorNumero(numero);
        
        if (piloto != null) {
            System.out.printf("\nEstadísticas de %s %s (%s):%n",
                piloto.getNombre(), piloto.getApellido(), piloto.getAbreviatura());
            System.out.printf("Nacionalidad: %s%n", piloto.getNacionalidad());
            System.out.printf("Edad: %d años%n", piloto.getEdad());
            System.out.printf("Equipo actual: %s%n", piloto.getEquipo().getNombre());
            System.out.printf("Campeonatos ganados: %d%n", piloto.getCampeonatosGanados());
            System.out.printf("Carreras disputadas: %d%n", piloto.getCarrerasDisputadas());
            System.out.printf("Victorias: %d%n", piloto.getCarrerasGanadas());
            System.out.printf("Podios: %d%n", piloto.getPodios());
            System.out.printf("Poles: %d%n", piloto.getPoles());
            System.out.printf("Vueltas rápidas: %d%n", piloto.getVueltasRapidas());
            System.out.printf("Puntos 2024: %d%n", piloto.getPuntos());
            
            System.out.println("\nResultados 2024:");
            piloto.getPosicionesPorCarrera().forEach((carrera, pos) ->
                System.out.printf("%s: %s%n", carrera, pos));
        } else {
            System.out.println("Piloto no encontrado.");
        }
    }

    private void mostrarResultadosCarrera() {
        System.out.println("\nResultados por carrera 2024:");
        datos.getPilotos().stream()
            .flatMap(p -> p.getPosicionesPorCarrera().entrySet().stream())
            .distinct()
            .sorted((e1, e2) -> e1.getKey().compareTo(e2.getKey()))
            .forEach(e -> {
                System.out.printf("\n%s:%n", e.getKey());
                datos.getPilotos().stream()
                    .filter(p -> p.getPosicionesPorCarrera().containsKey(e.getKey()))
                    .sorted((p1, p2) -> {
                        String pos1 = p1.getPosicionesPorCarrera().get(e.getKey());
                        String pos2 = p2.getPosicionesPorCarrera().get(e.getKey());
                        return pos1.compareTo(pos2);
                    })
                    .forEach(p -> System.out.printf("%s. %s %s (%s)%n",
                        p.getPosicionesPorCarrera().get(e.getKey()),
                        p.getNombre(),
                        p.getApellido(),
                        p.getEquipo().getNombre()));
            });
    }

    private void mostrarProximaCarrera() {
        datos.getProximaCarrera().ifPresent(carrera -> {
            System.out.println("\nPróxima carrera:");
            System.out.printf("Gran Premio de %s%n", carrera.getCircuito().getPais());
            System.out.printf("Circuito: %s%n", carrera.getCircuito().getNombre());
            System.out.printf("Longitud: %.3f km%n", carrera.getCircuito().getLongitud());
            System.out.printf("Vueltas: %d%n", carrera.getCircuito().getNumVueltas());
            System.out.printf("Clasificación: %s%n", 
                carrera.getCircuito().getFechaCarreraSprint().format(FORMATO_FECHA));
            System.out.printf("Carrera: %s%n",
                carrera.getCircuito().getFechaCarreraPrincipal().format(FORMATO_FECHA));
        });
    }

    private void mostrarInformacionEquipo() {
        System.out.println("\nEquipos de F1 2024:");
        datos.getEquipos().forEach(equipo -> {
            System.out.printf("\n%s:%n", equipo.getNombre());
            System.out.printf("Director: %s%n", equipo.getDirector());
            System.out.printf("País: %s%n", equipo.getPais());
            System.out.printf("Motor: %s%n", equipo.getMotorProveedor());
            System.out.printf("Campeonatos ganados: %d%n", equipo.getCampeonatosGanados());
            System.out.printf("Puntos 2024: %d%n", equipo.getPuntos());
            System.out.printf("Carreras ganadas 2024: %d%n", 
                equipo.getPosicionesPorCarrera().stream()
                    .filter(pos -> pos == 1)
                    .count());
            System.out.println("Pilotos:");
            equipo.getPilotos().forEach(p -> 
                System.out.printf("  - %s %s (#%d)%n", 
                    p.getNombre(), p.getApellido(), p.getNumero()));
            System.out.println();
        });
    }

    public static void main(String[] args) {
        MainUI ui = new MainUI();
        ui.mostrarMenu();
    }
} 