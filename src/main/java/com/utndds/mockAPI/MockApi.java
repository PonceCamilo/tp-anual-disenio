package com.utndds.mockAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import com.utndds.heladerasApi.models.Heladera.Punto;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class MockApi {

    @GetMapping("/api/puntos")
    public List<Punto> getPointsInRadius(
            @RequestParam("latitud") double latitud,
            @RequestParam("longitud") double longitud,
            @RequestParam("radio") double radio) {
        // Simular la generación de puntos dentro del radio especificado
        List<Punto> puntos = generarPuntos(latitud, longitud, radio);
        return puntos;
    }

    private List<Punto> generarPuntos(double latitud, double longitud, double radio) {
        // Simulación simple: generamos puntos aleatorios o dummy dentro del radio
        List<Punto> puntos = new ArrayList<>();
        puntos.add(new Punto(latitud + 0.01, longitud + 0.01, "heladera UTN Medrano", "Av. Medrano 951")); // dummy1
        puntos.add(new Punto(latitud - 0.01, longitud - 0.01, "heladera UTN Campus", "Mozart 2300")); // dummy2
        return puntos;
    }

    public static void main(String[] args) {
        SpringApplication.run(MockApi.class, args);
    }
}
