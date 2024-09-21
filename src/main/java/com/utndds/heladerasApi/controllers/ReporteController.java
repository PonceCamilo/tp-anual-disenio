package com.utndds.heladerasApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utndds.heladerasApi.services.GeneradorReportes.GeneradorReportes;

@RestController
@RequestMapping("/reportes")
public class ReporteController {
    @Autowired
    private GeneradorReportes generadorReportes;

    // En vez de generarlos tendria que enviar los reportes generados
    @PostMapping("/generarReportes")
    public ResponseEntity<String> generarReportes() {
        try {
            //generadorReportes.generarReportes();
            return ResponseEntity.ok("reporte generado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al generar el reporte: " + e.getMessage());
        }
    }
}