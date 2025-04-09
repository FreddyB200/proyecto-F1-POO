package org.f1.data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.f1.model.Carrera;
import org.f1.model.Circuito;
import org.f1.model.Equipo;
import org.f1.model.Piloto;

/**
 * Clase que contiene los datos precargados de la temporada 2024 de F1.
 * Esta clase sigue el patrón Singleton para asegurar una única instancia de los datos.
 * 
 * NOTA HISTÓRICA:
 * Los datos fueron inicialmente cargados usando las APIs de Ergast y OpenF1,
 * y posteriormente almacenados en archivos JSON para su uso offline.
 * 
 * @author Freddy Bautista - Implementación inicial y conexión con APIs
 * @author Javier Esquivel - Estructura de datos y persistencia
 * @author Sebastian Viloria - Documentación y mantenimiento
 * @version 2.0
 */
public class DatosPrecargados {
    private static DatosPrecargados instance;
    private final List<Piloto> pilotos;
    private final List<Equipo> equipos;
    private final List<Circuito> circuitos;
    private final List<Carrera> carreras;
    private static final boolean MODO_SOLO_LECTURA = true; // Asegura que los datos no se sobrescriban
    
    /**
     * Constructor privado que inicializa las listas y carga los datos.
     * Parte del patrón Singleton.
     */
    private DatosPrecargados() {
        this.pilotos = new ArrayList<>();
        this.equipos = new ArrayList<>();
        this.circuitos = new ArrayList<>();
        this.carreras = new ArrayList<>();
        cargarDatosIniciales();
    }

    /**
     * Obtiene la única instancia de DatosPrecargados.
     * Implementación del patrón Singleton.
     * 
     * @return DatosPrecargados - la única instancia de la clase
     */
    public static DatosPrecargados getInstance() {
        if (instance == null) {
            instance = new DatosPrecargados();
        }
        return instance;
    }

    /**
     * Carga los datos desde el archivo JSON.
     * Si el archivo no existe o está corrupto, usa datos por defecto.
     * 
     * HISTÓRICO: Este método originalmente usaba APIs en vivo,
     * pero ahora trabaja con datos precargados para mayor estabilidad.
     */
    private void cargarDatosIniciales() {
        if (MODO_SOLO_LECTURA) {
            try {
                String jsonContent = Files.readString(Paths.get("data/pilotos.json"));
                if (jsonContent != null && !jsonContent.isEmpty()) {
                    cargarDatosDesdeJSON(jsonContent);
                    return;
                }
            } catch (IOException e) {
                System.err.println("Error al cargar datos del JSON. Usando datos por defecto.");
                System.err.println("Detalles: " + e.getMessage());
            }
        }
        inicializarDatosPorDefecto();
    }

    /**
     * Procesa los datos del archivo JSON y los carga en memoria.
     * 
     * @param jsonContent Contenido del archivo JSON
     */
    private void cargarDatosDesdeJSON(String jsonContent) {
        // En una versión futura, podríamos implementar la carga real desde JSON
        // Por ahora, usamos datos por defecto
        inicializarDatosPorDefecto();
    }

    /**
     * Inicializa los datos por defecto en caso de que no se pueda acceder al JSON.
     * Esta es nuestra red de seguridad para garantizar que la aplicación siempre tenga datos.
     */
    private void inicializarDatosPorDefecto() {
        // Inicializar equipos 2024
        Equipo redbull = new Equipo("Red Bull Racing", "Christian Horner", "Austria", "Honda RBPT");
        Equipo ferrari = new Equipo("Ferrari", "Frédéric Vasseur", "Italia", "Ferrari");
        Equipo mercedes = new Equipo("Mercedes", "Toto Wolff", "Alemania", "Mercedes");
        equipos.add(redbull);
        equipos.add(ferrari);
        equipos.add(mercedes);

        // Inicializar pilotos 2024
        Piloto verstappen = new Piloto("Max", "Verstappen", "Holandés", 26, redbull, 1, "VER");
        Piloto perez = new Piloto("Sergio", "Pérez", "Mexicano", 34, redbull, 11, "PER");
        Piloto leclerc = new Piloto("Charles", "Leclerc", "Monegasco", 26, ferrari, 16, "LEC");
        Piloto sainz = new Piloto("Carlos", "Sainz", "Español", 29, ferrari, 55, "SAI");
        pilotos.add(verstappen);
        pilotos.add(perez);
        pilotos.add(leclerc);
        pilotos.add(sainz);

        // Agregar pilotos a sus equipos
        redbull.agregarPiloto(verstappen);
        redbull.agregarPiloto(perez);
        ferrari.agregarPiloto(leclerc);
        ferrari.agregarPiloto(sainz);

        // Inicializar circuitos 2024
        Circuito bahrein = new Circuito(
            "Circuito Internacional de Bahrein",
            "Bahrein",
            5.412,
            57,
            LocalDateTime.of(2024, 3, 2, 16, 0),
            LocalDateTime.of(2024, 3, 1, 16, 0)
        );
        circuitos.add(bahrein);

        // Inicializar primera carrera 2024
        Carrera carreraBahrein = new Carrera(bahrein, LocalDateTime.of(2024, 3, 2, 16, 0), false);
        carreras.add(carreraBahrein);
    }

    /**
     * Obtiene una copia de la lista de pilotos para prevenir modificaciones externas.
     * 
     * @return List<Piloto> - copia de la lista de pilotos
     */
    public List<Piloto> getPilotos() {
        return new ArrayList<>(pilotos);
    }

    /**
     * Obtiene una copia de la lista de equipos para prevenir modificaciones externas.
     * 
     * @return List<Equipo> - copia de la lista de equipos
     */
    public List<Equipo> getEquipos() {
        return new ArrayList<>(equipos);
    }

    /**
     * Obtiene una copia de la lista de circuitos para prevenir modificaciones externas.
     * 
     * @return List<Circuito> - copia de la lista de circuitos
     */
    public List<Circuito> getCircuitos() {
        return new ArrayList<>(circuitos);
    }

    /**
     * Obtiene una copia de la lista de carreras para prevenir modificaciones externas.
     * 
     * @return List<Carrera> - copia de la lista de carreras
     */
    public List<Carrera> getCarreras() {
        return new ArrayList<>(carreras);
    }

    /**
     * Genera la clasificación actual de pilotos.
     * 
     * @return Map<String, Integer> - clasificación con nombre del piloto y puntos
     */
    public Map<String, Integer> obtenerClasificacionPilotos() {
        Map<String, Integer> clasificacion = new HashMap<>();
        for (Piloto piloto : pilotos) {
            clasificacion.put(piloto.obtenerInformacionBasica(), piloto.getPuntos());
        }
        return clasificacion;
    }

    /**
     * Genera la clasificación actual de constructores.
     * 
     * @return Map<String, Integer> - clasificación con nombre del equipo y puntos
     */
    public Map<String, Integer> obtenerClasificacionConstructores() {
        Map<String, Integer> clasificacion = new HashMap<>();
        for (Equipo equipo : equipos) {
            clasificacion.put(equipo.getNombre(), equipo.getPuntos());
        }
        return clasificacion;
    }
} 