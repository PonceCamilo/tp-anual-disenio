package com.utndds.heladerasApi.services;

import com.utndds.heladerasApi.controllers.DTOs.RecomendacionDTO;
import com.utndds.heladerasApi.models.Heladera.Punto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class RecomendacionPuntosService {

    @Autowired
    private RestTemplate restTemplate;

    private static final double RADIO_TIERRA = 6371000; // radio de la tierra en metros

    public List<RecomendacionDTO> getRecomendaciones(double latitud, double longitud, double radio) {
        List<RecomendacionDTO> listaRecomendaciones = new ArrayList<>();
        Random random = new Random();

        /* TEST (supongo) */

        for (int i = 0; i < 10; i++) {
            // Genera un angulo aleatorio entre 0 y 2pi
            double theta = 2 * Math.PI * random.nextDouble();

            // Genera una distancia aleatoria desde el centro
            double r = radio * Math.sqrt(random.nextDouble());

            // Pasa de coordenadas polares a x/y
            double dx = r * Math.cos(theta);
            double dy = r * Math.sin(theta);

            // Convierte x/y a una latitud y una longitud
            double newLatitud = latitud + (dy / RADIO_TIERRA) * (180 / Math.PI);
            double newLongitud = longitud + (dx / (RADIO_TIERRA * Math.cos(Math.toRadians(latitud)))) * (180 / Math.PI);

            String nombre = "Punto " + (i + 1);
            RecomendacionDTO recomendacion = new RecomendacionDTO(nombre, newLatitud, newLongitud);
            listaRecomendaciones.add(recomendacion);
        }

        return listaRecomendaciones;
    }
}