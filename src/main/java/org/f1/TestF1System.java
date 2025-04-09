package org.f1;

import java.util.ArrayList;
import java.util.List;

import org.f1.api.APIClient;
import org.f1.data.DataManager;
import org.f1.model.Equipo;
import org.f1.model.Piloto;

public class TestF1System {
    private static DataManager dataManager;
    private static APIClient apiClient;

    public static void main(String[] args) {
        System.out.println("Iniciando pruebas del sistema F1...\n");
        
        dataManager = new DataManager();
        apiClient = new APIClient();

        // Test 1: Crear y guardar datos de prueba
        testCrearYGuardarDatos();

        // Test 2: Cargar y mostrar datos guardados
        testCargarYMostrarDatos();

        // Test 3: Probar carga desde API
        testCargarDatosAPI();

        System.out.println("\nPruebas completadas.");
    }

    private static void testCrearYGuardarDatos() {
        System.out.println("Test 1: Crear y guardar datos de prueba");
        try {
            List<Piloto> pilotos = new ArrayList<>();

            // Crear equipos de prueba
            Equipo redbull = new Equipo("Red Bull Racing", "Christian Horner", "Reino Unido", "Honda RBPT");
            Equipo ferrari = new Equipo("Ferrari", "Frédéric Vasseur", "Italia", "Ferrari");

            // Crear pilotos de prueba
            Piloto verstappen = new Piloto("Max", "Verstappen", "Holandés", 26, redbull, 1, "VER");
            Piloto perez = new Piloto("Sergio", "Pérez", "Mexicano", 34, redbull, 11, "PER");
            Piloto leclerc = new Piloto("Charles", "Leclerc", "Monegasco", 26, ferrari, 16, "LEC");
            Piloto sainz = new Piloto("Carlos", "Sainz", "Español", 29, ferrari, 55, "SAI");

            // Agregar pilotos a la lista
            pilotos.add(verstappen);
            pilotos.add(perez);
            pilotos.add(leclerc);
            pilotos.add(sainz);

            // Guardar pilotos
            dataManager.guardarPilotos(pilotos);
            System.out.println("✅ Datos de prueba creados y guardados correctamente");

        } catch (Exception e) {
            System.err.println("Error en Test 1: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void testCargarYMostrarDatos() {
        System.out.println("\nTest 2: Cargar y mostrar datos guardados");
        try {
            List<Piloto> pilotos = dataManager.cargarPilotos();
            System.out.println("Pilotos cargados: " + pilotos.size());
            
            for (Piloto piloto : pilotos) {
                System.out.printf("Piloto: %s %s, Equipo: %s, Número: %d%n",
                    piloto.getNombre(),
                    piloto.getApellido(),
                    piloto.getEquipo() != null ? piloto.getEquipo().getNombre() : "Sin equipo",
                    piloto.getNumero());
            }
            System.out.println("✅ Datos cargados y mostrados correctamente");

        } catch (Exception e) {
            System.err.println("Error en Test 2: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void testCargarDatosAPI() {
        System.out.println("\nTest 3: Probar carga desde API");
        try {
            String datosAPI = apiClient.fetchDrivers2024();
            System.out.println("Datos obtenidos de la API:");
            System.out.println(datosAPI);
            System.out.println("✅ Datos de API obtenidos correctamente");

        } catch (Exception e) {
            System.err.println("Error en Test 3: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 