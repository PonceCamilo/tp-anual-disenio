package com.utndds.heladerasApi.controllers;

import com.utndds.heladerasApi.models.Enums.MotivoApertura;
import com.utndds.heladerasApi.models.Solicitudes.SolicitudApertura;
import com.utndds.heladerasApi.services.AperturaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aperturas")
public class AperturaController {

    @Autowired
    private AperturaService aperturaService;

    @PostMapping("/solicitud")
    public ResponseEntity<SolicitudApertura> crearSolicitudApertura(
            @RequestParam("colaboradorUUID") String colaboradorUUID,
            @RequestParam("heladeraId") Long heladeraId,
            @RequestParam("motivo") MotivoApertura motivo) {

        SolicitudApertura solicitudApertura = aperturaService.crearSolicitud(colaboradorUUID, heladeraId, motivo);
        return ResponseEntity.ok(solicitudApertura);
    }
}