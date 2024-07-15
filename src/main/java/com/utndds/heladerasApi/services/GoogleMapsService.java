package com.utndds.heladerasApi.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utndds.heladerasApi.models.Heladera.Ubicacion;

import org.springframework.stereotype.Service;

@Service
public class GoogleMapsService {

    public List<Ubicacion> obtenerUbicaciones() {
        List<Ubicacion> ubicaciones = new ArrayList<>();

        try {

            InputStream inputStream = getClass().getResourceAsStream("/direcciones-heladeras.json");

            ObjectMapper objectMapper = new ObjectMapper();
            Ubicacion[] ubicacionesArray = objectMapper.readValue(inputStream, Ubicacion[].class);

            for (Ubicacion ubicacion : ubicacionesArray) {
                ubicaciones.add(ubicacion);
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
        return ubicaciones;
    }
}
