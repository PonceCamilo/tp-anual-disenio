package com.utndds.mockAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import com.utndds.heladerasApi.models.Heladera.Ubicacion;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class MockApi {

    @GetMapping("/api/puntos")
    public List<Ubicacion> getPointsInRadius(
            @RequestParam("latitud") double latitud,
            @RequestParam("longitud") double longitud,
            @RequestParam("radio") double radio) {
        // Simular la generación de puntos dentro del radio especificado
        List<Ubicacion> puntos = generarPuntos(latitud, longitud, radio);
        return puntos;
    }

    private List<Ubicacion> generarPuntos(double latitud, double longitud, double radio) {
        // Simulación simple: generamos puntos aleatorios o dummy dentro del radio
        List<Ubicacion> puntos = new ArrayList<>();
        puntos.add(new Ubicacion(latitud + 0.01, longitud + 0.01)); // Ejemplo de punto generado
        puntos.add(new Ubicacion(latitud - 0.01, longitud - 0.01)); // Otro ejemplo de punto generado
        return puntos;
    }

    public static void main(String[] args) {
        SpringApplication.run(MockApi.class, args);
    }
}
