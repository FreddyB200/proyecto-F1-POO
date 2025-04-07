package org.f1.ui;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.f1.api.APIClient;
import org.f1.data.DataManager;
import org.f1.model.Piloto;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class MainUI {
    private final APIClient apiClient;
    private final DataManager dataManager;
    private final Scanner scanner;

    public MainUI() {
        this.apiClient = new APIClient();
        this.dataManager = new DataManager();
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        while (true) {
            System.out.println("\n=== F1 Stats 2024 ===");
            System.out.println("1. Cargar pilotos desde API");
            System.out.println("2. Mostrar lista de pilotos guardados");
            System.out.println("3. Ver clasificación de pilotos");
            System.out.println("4. Ver clasificación de constructores");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    cargarPilotosDesdeAPI();
                    break;
                case 2:
                    mostrarPilotosGuardados();
                    break;
                case 3:
                    mostrarClasificacionPilotos();
                    break;
                case 4:
                    mostrarClasificacionConstructores();
                    break;
                case 5:
                    System.out.println("¡Hasta luego!");
                    return;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    private void cargarPilotosDesdeAPI() {
        try {
            String datosJSON = apiClient.fetchDrivers2024();
            System.out.println("Datos obtenidos de la API:");
            System.out.println(datosJSON);

            // Parsear el JSON a objetos Piloto
            JsonObject jsonObject = JsonParser.parseString(datosJSON).getAsJsonObject();
            JsonObject driverTable = jsonObject.getAsJsonObject("MRData").getAsJsonObject("DriverTable");
            String driversJson = driverTable.get("Drivers").toString();

            Gson gson = new Gson();
            Type pilotoListType = new TypeToken<ArrayList<Piloto>>(){}.getType();
            List<Piloto> pilotos = gson.fromJson(driversJson, pilotoListType);

            // Guardar los pilotos en el archivo JSON
            dataManager.guardarPilotos(pilotos);
            System.out.println("Pilotos guardados exitosamente.");

        } catch (IOException | InterruptedException e) {
            System.err.println("Error al cargar datos de la API: " + e.getMessage());
            System.out.println("Intentando cargar datos locales...");
            mostrarPilotosGuardados();
        }
    }

    private void mostrarPilotosGuardados() {
        List<Piloto> pilotos = dataManager.cargarPilotos();
        if (pilotos.isEmpty()) {
            System.out.println("No hay pilotos guardados.");
            return;
        }

        System.out.println("\nPilotos guardados:");
        for (Piloto piloto : pilotos) {
            System.out.printf("%s %s - %s (#%d)%n",
                piloto.getNombre(),
                piloto.getApellido(),
                piloto.getEquipo().getNombre(),
                piloto.getNumero());
        }
    }

    private void mostrarClasificacionPilotos() {
        List<Piloto> pilotos = dataManager.cargarPilotos();
        if (pilotos.isEmpty()) {
            System.out.println("No hay datos de clasificación disponibles.");
            return;
        }

        System.out.println("\nClasificación de Pilotos:");
        pilotos.stream()
            .sorted((p1, p2) -> p2.getPuntos() - p1.getPuntos())
            .forEach(p -> System.out.printf("%s %s - %d puntos%n",
                p.getNombre(),
                p.getApellido(),
                p.getPuntos()));
    }

    private void mostrarClasificacionConstructores() {
        // TODO: Implementar la lógica para mostrar la clasificación de constructores
        System.out.println("Funcionalidad en desarrollo...");
    }

    public static void main(String[] args) {
        MainUI ui = new MainUI();
        ui.mostrarMenu();
    }
} 