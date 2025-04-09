package org.f1.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * COMPONENTE HISTÓRICO - NO ACTIVO
 * 
 * Cliente que se utilizó inicialmente para interactuar con la API de Ergast F1.
 * Este código se mantiene como referencia histórica del proceso de desarrollo
 * y como documentación de la fase inicial del proyecto.
 *
 * NOTA: La API de Ergast será deprecada en 2025.
 * Los datos ahora se mantienen en archivos JSON locales.
 *
 * @author Freddy Bautista - Implementación inicial
 * @author Sebastian Viloria - Documentación histórica
 * @version 2.0 (Histórica)
 * @deprecated Este componente ya no está en uso activo.
 *             Los datos se mantienen localmente en JSON.
 */
@Deprecated
public class APIClient {
    private static final String BASE_URL = "http://ergast.com/api/f1";
    private final HttpClient client;
    private static final int TIMEOUT_SECONDS = 30;
    private static final int MAX_RETRIES = 3;
    private static final long RATE_LIMIT_DELAY = 1000; // 1 second between calls
    private static final boolean COMPONENTE_DESACTIVADO = true;

    /**
     * Constructor histórico del cliente API.
     * 
     * @deprecated Este componente ya no está en uso activo.
     */
    @Deprecated
    public APIClient() {
        this.client = HttpClient.newHttpClient();
    }

    /**
     * MÉTODO HISTÓRICO
     * Originalmente usado para obtener datos de pilotos de la temporada 2024.
     * Este método se mantiene como documentación del proceso inicial de carga de datos.
     *
     * @return String JSON con los datos de los pilotos (histórico)
     * @throws IOException si ocurre un error de entrada/salida
     * @throws InterruptedException si la operación es interrumpida
     * @deprecated Este método ya no está en uso activo
     */
    @Deprecated
    public String fetchDrivers2024() throws IOException, InterruptedException {
        if (COMPONENTE_DESACTIVADO) {
            throw new UnsupportedOperationException(
                "Este componente está desactivado. Los datos ahora se mantienen localmente en JSON. " +
                "Consulte DatosPrecargados.java para acceder a los datos actuales."
            );
        }

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