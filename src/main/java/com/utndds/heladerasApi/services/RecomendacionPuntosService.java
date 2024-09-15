package com.utndds.heladerasApi.services;

import com.utndds.heladerasApi.controllers.DTOs.RecomendacionDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Service
public class RecomendacionPuntosService {

    private final RestTemplate restTemplate;

    public RecomendacionPuntosService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<RecomendacionDTO> getRecomendaciones(double latitud, double longitud, double radio) {
        String url = String.format(Locale.US,
                "http://localhost:8081/mockAPI/recomendarPuntos?latitud=%f&longitud=%f&radio=%f", latitud, longitud,
                radio);

        ResponseEntity<RecomendacionDTO[]> response = restTemplate.getForEntity(url, RecomendacionDTO[].class);
        RecomendacionDTO[] recomendacionesArray = response.getBody();

        // Verificamos si la respuesta es nula o vacía
        return Arrays.asList(recomendacionesArray);
    }
}