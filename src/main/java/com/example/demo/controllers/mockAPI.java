package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTOs.RecomendacionDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/mockAPI")
public class mockAPI {

    private static final double RADIO_TIERRA = 6371000; // radio de la tierra en metros

    @GetMapping("/recomendarPuntos")
    public List<RecomendacionDTO> getRecomendaciones(
            @RequestParam double latitud,
            @RequestParam double longitud,
            @RequestParam double radio) {

        System.out.println(latitud);
        System.out.println(longitud);
        System.out.println(radio);
        List<RecomendacionDTO> listaRecomendaciones = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            double theta = 2 * Math.PI * random.nextDouble();
            double r = radio * Math.sqrt(random.nextDouble());
            double dx = r * Math.cos(theta);
            double dy = r * Math.sin(theta);
            double newLatitud = latitud + (dy / RADIO_TIERRA) * (180 / Math.PI);
            double newLongitud = longitud + (dx / (RADIO_TIERRA * Math.cos(Math.toRadians(latitud)))) * (180 / Math.PI);
            String nombre = "Punto " + (i + 1);
            RecomendacionDTO recomendacion = new RecomendacionDTO(nombre, newLatitud, newLongitud);
            listaRecomendaciones.add(recomendacion);
        }

        return listaRecomendaciones;
    }
}