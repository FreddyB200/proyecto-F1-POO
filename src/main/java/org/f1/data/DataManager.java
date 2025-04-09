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

/**
 * Gestor de persistencia de datos para el sistema F1.
 * 
 * NOTA HISTÓRICA:
 * Esta clase fue inicialmente diseñada para sincronizar datos con APIs externas.
 * Actualmente opera en modo solo lectura, preservando los datos precargados.
 * 
 * @author Freddy Bautista - Diseño inicial y conexión API
 * @author Javier Esquivel - Implementación de persistencia
 * @author Sebastian Viloria - Documentación y mantenimiento
 * @version 2.0
 */
public class DataManager {
    private static final String DATA_DIR = "data";
    private static final String PILOTOS_FILE = "pilotos.json";
    private static final boolean MODO_SOLO_LECTURA = true;
    private final Gson gson;

    /**
     * Constructor que inicializa el gestor de JSON con la configuración necesaria.
     */
    public DataManager() {
        this.gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(Equipo.class, new EquipoTypeAdapter())
            .create();
        createDataDirectoryIfNotExists();
    }

    /**
     * Crea el directorio de datos si no existe.
     */
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

    /**
     * MÉTODO HISTÓRICO - Actualmente deshabilitado
     * Originalmente usado para guardar datos de las APIs.
     * Mantenido por razones de documentación y posible uso futuro.
     * 
     * @param pilotos Lista de pilotos a guardar
     */
    public void guardarPilotos(List<Piloto> pilotos) {
        if (MODO_SOLO_LECTURA) {
            System.out.println("AVISO: Sistema en modo solo lectura. No se pueden modificar los datos.");
            return;
        }

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

    /**
     * Carga los datos de pilotos desde el archivo JSON.
     * Este método es seguro y solo lee datos existentes.
     * 
     * @return List<Piloto> Lista de pilotos cargada del JSON
     */
    public List<Piloto> cargarPilotos() {
        Path filePath = Paths.get(DATA_DIR, PILOTOS_FILE);
        
        if (!Files.exists(filePath)) {
            System.err.println("AVISO: No se encontró el archivo de pilotos en: " + filePath.toAbsolutePath());
            return new ArrayList<>();
        }

        try {
            String jsonContent = Files.readString(filePath);
            if (jsonContent.trim().isEmpty()) {
                System.err.println("AVISO: El archivo de pilotos está vacío.");
                return new ArrayList<>();
            }
            
            Piloto[] pilotos = gson.fromJson(jsonContent, Piloto[].class);
            if (pilotos == null) {
                System.err.println("Error al parsear el JSON de pilotos.");
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

    /**
     * Adaptador personalizado para serialización/deserialización de Equipo.
     */
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
                    case "nombre" -> equipo.setNombre(in.nextString());
                    case "director" -> equipo.setDirector(in.nextString());
                    case "pais" -> equipo.setPais(in.nextString());
                    case "motorProveedor" -> equipo.setMotorProveedor(in.nextString());
                    case "campeonatosGanados" -> equipo.setCampeonatosGanados(in.nextInt());
                    case "puntosConstructores2024" -> equipo.sumarPuntos(in.nextInt());
                    default -> in.skipValue();
                }
            }
            
            in.endObject();
            return equipo;
        }
    }
} 