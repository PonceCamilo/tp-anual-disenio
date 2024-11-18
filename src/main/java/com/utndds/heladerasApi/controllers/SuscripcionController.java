package com.utndds.heladerasApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.utndds.heladerasApi.DTOs.SuscripcionDTO;
import com.utndds.heladerasApi.models.Suscripciones.Suscripcion;
import com.utndds.heladerasApi.services.SuscripcionService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/suscripciones")
public class SuscripcionController {

    @Autowired
    private SuscripcionService suscripcionService;

    @PostMapping("/suscribir")
    public ResponseEntity<Suscripcion> suscribirColaborador(@RequestBody SuscripcionDTO suscripcionDTO) {
        System.out.println("entre");
        Suscripcion suscripcion = suscripcionService.suscribirColaborador(suscripcionDTO);
        return ResponseEntity.ok(suscripcion);
    }
}
