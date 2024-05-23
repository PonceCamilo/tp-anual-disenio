package com.utndds.heladerasApi.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utndds.heladerasApi.models.Heladera.UbicacionGoogleMaps;

import org.springframework.stereotype.Service;

@Service
public class GoogleMapsService {

    public List<UbicacionGoogleMaps> obtenerUbicaciones() {
        List<UbicacionGoogleMaps> ubicaciones = new ArrayList<>();

        try {

            InputStream inputStream = getClass().getResourceAsStream("/direcciones-heladeras.json");

            ObjectMapper objectMapper = new ObjectMapper();
            UbicacionGoogleMaps[] ubicacionesArray = objectMapper.readValue(inputStream, UbicacionGoogleMaps[].class);

            for (UbicacionGoogleMaps ubicacion : ubicacionesArray) {
                ubicaciones.add(ubicacion);
            }

            System.out.println("Contenido de la lista de ubicaciones:");
            for (UbicacionGoogleMaps ubicacion : ubicaciones) {
                System.out.println("Nombre: " + ubicacion.getNombre());

                System.out.println("Provincia: " + ubicacion.getProvincia());
                System.out.println("--------------------------------------");
            }
        } catch (IOException e) {

            e.printStackTrace();
        }

        return ubicaciones;
    }
}
