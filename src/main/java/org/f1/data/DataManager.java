package org.f1.data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.f1.model.Piloto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DataManager {
    private static final String DATA_DIR = "data";
    private static final String PILOTOS_FILE = "pilotos.json";
    private final Gson gson;

    public DataManager() {
        this.gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();
        createDataDirectoryIfNotExists();
    }

    private void createDataDirectoryIfNotExists() {
        Path dataPath = Paths.get(DATA_DIR);
        if (!Files.exists(dataPath)) {
            try {
                Files.createDirectory(dataPath);
            } catch (IOException e) {
                System.err.println("Error al crear directorio de datos: " + e.getMessage());
            }
        }
    }

    public void guardarPilotos(List<Piloto> pilotos) {
        String jsonPilotos = gson.toJson(pilotos);
        Path filePath = Paths.get(DATA_DIR, PILOTOS_FILE);
        
        try {
            Files.writeString(filePath, jsonPilotos);
        } catch (IOException e) {
            System.err.println("Error al guardar pilotos: " + e.getMessage());
        }
    }

    public List<Piloto> cargarPilotos() {
        Path filePath = Paths.get(DATA_DIR, PILOTOS_FILE);
        
        if (!Files.exists(filePath)) {
            return new ArrayList<>();
        }

        try {
            String jsonContent = Files.readString(filePath);
            Piloto[] pilotos = gson.fromJson(jsonContent, Piloto[].class);
            return new ArrayList<>(List.of(pilotos));
        } catch (IOException e) {
            System.err.println("Error al cargar pilotos: " + e.getMessage());
            return new ArrayList<>();
        }
    }
} 