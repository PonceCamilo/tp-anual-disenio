package com.utndds.heladerasApi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;

import com.utndds.heladerasApi.models.Heladera.Ubicacion;
import com.utndds.heladerasApi.services.GoogleMapsService;
import org.springframework.beans.factory.annotation.Autowired;

@CrossOrigin(origins = "*")
@RestController
public class GoogleMapsController {

    @Autowired
    private GoogleMapsService googleMapsService = new GoogleMapsService();

    @GetMapping("/ubicaciones-googlemaps")
    public List<Ubicacion> obtenerUbicacionesGoogleMaps() {
        return googleMapsService.obtenerUbicaciones();
    }
}
