package com.utndds.heladerasApi.services;

import com.utndds.heladerasApi.models.Heladera.Ubicacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RecomendacionService {

    @Autowired
    private RestTemplate restTemplate;

    public List<Ubicacion> getRecomendaciones(double latitud, double longitud, double radio) {
        String url = String.format("http://localhost:8080/api/puntos?latitud=%f&longitud=%f&radio=%f", latitud,
                longitud, radio);
        ResponseEntity<List<Ubicacion>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Ubicacion>>() {
                });
        return response.getBody();
    }
}