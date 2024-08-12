package com.utndds.heladerasApi.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utndds.heladerasApi.models.Heladera.Punto;

import org.springframework.stereotype.Service;

@Service
public class GoogleMapsService {
    private static volatile GoogleMapsService instancia;

    // Constructor privado para evitar la instanciación directa
    private GoogleMapsService() {
    }

    // Método público para obtener la instancia única
    public static GoogleMapsService getInstance() {
        if (instancia == null) {
            synchronized (GoogleMapsService.class) {
                if (instancia == null) {
                    instancia = new GoogleMapsService();
                }
            }
        }
        return instancia;
    }

    public List<Punto> obtenerUbicaciones() {
        List<Punto> ubicaciones = new ArrayList<>();

        try {

            InputStream inputStream = getClass().getResourceAsStream("/direcciones-heladeras.json");

            ObjectMapper objectMapper = new ObjectMapper();
            Punto[] ubicacionesArray = objectMapper.readValue(inputStream, Punto[].class);

            for (Punto ubicacion : ubicacionesArray) {
                ubicaciones.add(ubicacion);
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
        return ubicaciones;
    }
}
