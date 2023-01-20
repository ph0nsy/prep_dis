package com.ufv.plantilla;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class API {
    public String urlPrefix = "http://localhost:8081";
    public String getZonasMayores() throws URISyntaxException, IOException, InterruptedException {
        String urlFull = urlPrefix + "/ZonasBasicasMayores";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(urlFull))
                .GET()
                .build();

        HttpResponse<String> response = HttpClient
                .newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
    String codigo_geometria;
    private String zona_basica_salud;
    private Number tasa_incidencia_acumulada_P60mas_ultimos_14dias;
    private Number casos_confirmados_P60mas_ultimos_14dias;
    private String fecha_informe;

    public void postMayores(int posicion, String codigo_geometria, String zona_basica_salud, Number tasa_incidencia_acumulada_P60mas_ultimos_14dias, Number casos_confirmados_P60mas_ultimos_14dias, String fecha_informe) throws IOException, InterruptedException {
        String urlFull = urlPrefix + "/ZonasBasicasSaludMayores";
        var values = new HashMap<String, String>() {{
            put("posicion", String.valueOf(posicion));
            put ("codigo_geometria", codigo_geometria);
            put ("zona_basica_salud", zona_basica_salud);
            put ("tasa_incidencia_acumulada_P60mas_ultimos_14dias", String.valueOf(tasa_incidencia_acumulada_P60mas_ultimos_14dias));
            put ("casos_confirmados_P60mas_ultimos_14dias", String.valueOf(casos_confirmados_P60mas_ultimos_14dias));
            put ("fecha_informe", fecha_informe);
        }};

        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writeValueAsString(values);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlFull))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = HttpClient
                .newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

    }

    public void deleteMayores(int index) throws IOException, InterruptedException {
        String urlFull = urlPrefix + "/ZonasBasicasSalud/" + index;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlFull))
                .DELETE()
                .build();

        HttpResponse response = HttpClient
                .newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());
    }
}
