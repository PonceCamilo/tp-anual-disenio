package com.utndds.heladerasApi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.utndds.heladerasApi.DTOs.PuntoMapaDTO;
import com.utndds.heladerasApi.services.GoogleMapsService;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
public class GoogleMapsController {

    @Autowired
    private GoogleMapsService googleMapsService;

    @GetMapping("/ubicaciones-googlemaps")
    public List<PuntoMapaDTO> obtenerUbicacionesGoogleMaps() {
        return googleMapsService.obtenerUbicaciones();
    }
}
