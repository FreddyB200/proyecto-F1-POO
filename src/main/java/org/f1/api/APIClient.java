package org.f1.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APIClient {
    private static final String BASE_URL = "https://api.openf1.org/v1";
    private final HttpClient client;

    public APIClient() {
        this.client = HttpClient.newHttpClient();
    }

    public String fetchDrivers2024() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(BASE_URL + "/drivers?year=2024"))
            .GET()
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        if (response.statusCode() != 200) {
            throw new IOException("Error al obtener datos de pilotos: " + response.statusCode());
        }

        return response.body();
    }
} 