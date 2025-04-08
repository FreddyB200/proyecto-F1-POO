package org.f1.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Cliente para interactuar con la API de Ergast F1.
 * Proporciona métodos para obtener datos de pilotos de la temporada 2024.
 *
 * @author Equipo de Desarrollo F1
 * @version 1.0
 */
public class APIClient {
    private static final String BASE_URL = "http://ergast.com/api/f1";
    private final HttpClient client;
    private static final int TIMEOUT_SECONDS = 30;
    private static final int MAX_RETRIES = 3;
    private static final long RATE_LIMIT_DELAY = 1000; // 1 second between calls

    public APIClient() {
        this.client = HttpClient.newHttpClient();
    }

    /**
     * Obtiene los datos de los pilotos de la temporada 2024 desde la API de Ergast.
     *
     * @return JSON con los datos de los pilotos
     * @throws IOException si ocurre un error de entrada/salida
     * @throws InterruptedException si la operación es interrumpida
     */
    public String fetchDrivers2024() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(BASE_URL + "/2024/drivers.json"))
            .GET()
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        if (response.statusCode() != 200) {
            throw new IOException("Error al obtener datos de pilotos: " + response.statusCode());
        }

        return response.body();
    }
} 