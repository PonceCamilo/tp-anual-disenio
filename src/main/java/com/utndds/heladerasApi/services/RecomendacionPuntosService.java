package com.utndds.heladerasApi.services;

import com.utndds.heladerasApi.models.Heladera.Punto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class RecomendacionPuntosService {
    private static volatile RecomendacionPuntosService instancia;

    @Autowired
    private RestTemplate restTemplate;

    // Constructor privado para evitar la instanciación directa
    private RecomendacionPuntosService() {
    }

    // Método público para obtener la instancia única
    public static RecomendacionPuntosService getInstance() {
        if (instancia == null) {
            synchronized (RecomendacionPuntosService.class) {
                if (instancia == null) {
                    instancia = new RecomendacionPuntosService();
                }
            }
        }
        return instancia;
    }

    public List<Punto> getRecomendaciones(double latitud, double longitud, double radio) {
        String url = "http://localhost:8080/api/puntos?latitud=1&longitud=1&radio=1";
        try {
            ResponseEntity<List<Punto>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Punto>>() {
                    });
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}