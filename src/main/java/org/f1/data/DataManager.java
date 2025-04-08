package org.f1.data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.f1.model.Equipo;
import org.f1.model.Piloto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class DataManager {
    private static final String DATA_DIR = "data";
    private static final String PILOTOS_FILE = "pilotos.json";
    private final Gson gson;

    public DataManager() {
        this.gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(Equipo.class, new EquipoTypeAdapter())
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
        try {
            String jsonPilotos = gson.toJson(pilotos);
            Path filePath = Paths.get(DATA_DIR, PILOTOS_FILE);
            Files.writeString(filePath, jsonPilotos);
            System.out.println("Pilotos guardados exitosamente en: " + filePath.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error al guardar pilotos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Piloto> cargarPilotos() {
        Path filePath = Paths.get(DATA_DIR, PILOTOS_FILE);
        
        if (!Files.exists(filePath)) {
            System.out.println("No se encontró el archivo de pilotos. Se creará uno nuevo.");
            return new ArrayList<>();
        }

        try {
            String jsonContent = Files.readString(filePath);
            if (jsonContent.trim().isEmpty()) {
                System.out.println("El archivo de pilotos está vacío.");
                return new ArrayList<>();
            }
            
            Piloto[] pilotos = gson.fromJson(jsonContent, Piloto[].class);
            if (pilotos == null) {
                System.out.println("Error al parsear el JSON de pilotos.");
                return new ArrayList<>();
            }
            
            return new ArrayList<>(List.of(pilotos));
        } catch (IOException e) {
            System.err.println("Error al cargar pilotos: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        } catch (Exception e) {
            System.err.println("Error inesperado al cargar pilotos: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private static class EquipoTypeAdapter extends TypeAdapter<Equipo> {
        @Override
        public void write(JsonWriter out, Equipo equipo) throws IOException {
            if (equipo == null) {
                out.nullValue();
                return;
            }
            out.beginObject();
            out.name("nombre").value(equipo.getNombre());
            out.name("director").value(equipo.getDirector());
            out.name("pais").value(equipo.getPais());
            out.name("motorProveedor").value(equipo.getMotorProveedor());
            out.name("campeonatosGanados").value(equipo.getCampeonatosGanados());
            out.name("puntosConstructores2024").value(equipo.getPuntosConstructores2024());
            out.endObject();
        }

        @Override
        public Equipo read(JsonReader in) throws IOException {
            if (in.peek() == null) {
                in.nextNull();
                return null;
            }
            
            in.beginObject();
            Equipo equipo = new Equipo();
            
            while (in.hasNext()) {
                String name = in.nextName();
                switch (name) {
                    case "nombre":
                        equipo.setNombre(in.nextString());
                        break;
                    case "director":
                        equipo.setDirector(in.nextString());
                        break;
                    case "pais":
                        equipo.setPais(in.nextString());
                        break;
                    case "motorProveedor":
                        equipo.setMotorProveedor(in.nextString());
                        break;
                    case "campeonatosGanados":
                        equipo.setCampeonatosGanados(in.nextInt());
                        break;
                    case "puntosConstructores2024":
                        equipo.sumarPuntos(in.nextInt());
                        break;
                    default:
                        in.skipValue();
                        break;
                }
            }
            
            in.endObject();
            return equipo;
        }
    }
} 