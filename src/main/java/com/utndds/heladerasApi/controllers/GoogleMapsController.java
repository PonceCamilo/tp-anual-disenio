package com.utndds.heladerasApi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;

import com.utndds.heladerasApi.models.Heladera.UbicacionGoogleMaps;
import com.utndds.heladerasApi.services.GoogleMapsService;

@CrossOrigin(origins = "*")
@RestController
public class GoogleMapsController {

    private final GoogleMapsService googleMapsService;

    public GoogleMapsController(GoogleMapsService googleMapsService) {
        this.googleMapsService = googleMapsService;
    }

    @GetMapping("/ubicaciones-googlemaps")
    public List<UbicacionGoogleMaps> obtenerUbicacionesGoogleMaps() {
        return googleMapsService.obtenerUbicaciones();
    }
}