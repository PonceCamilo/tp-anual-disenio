package com.utndds.heladerasApi.controllers;

import com.utndds.heladerasApi.services.Canjes.CalculadoraPuntosService;
import com.utndds.heladerasApi.services.Canjes.CanjesService;

import jakarta.persistence.EntityNotFoundException;

import java.util.Map;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/canjes")
public class CanjesController {

    @Autowired
    private CanjesService canjesService;

    @Autowired
    private CalculadoraPuntosService calculadoraPuntosService;

    // Endpoint para canjear una oferta
    @PostMapping("/canjear")
    public ResponseEntity<String> canjearOferta(@RequestParam String colaboradorUUID, @RequestParam Long ofertaId) {
        try {
            boolean canjeExitoso = canjesService.canjearOferta(colaboradorUUID, ofertaId);

            if (canjeExitoso) {
                return ResponseEntity.ok("Canje realizado con éxito.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("No tienes suficientes puntos para canjear esta oferta.");
            }
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrió un error al procesar el canje.");
        }
    }

    // Endpoint para calcular los puntos de un colaborador
    @GetMapping("/calcularPuntos")
    public ResponseEntity<Double> calcularPuntosColaborador(@RequestParam String colaboradorUUID) {
        try {
            double puntos = calculadoraPuntosService.calcularPuntos(colaboradorUUID);
            return ResponseEntity.ok(puntos);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/ofertasDisponibles")
    public ResponseEntity<?> ofertasDisponibles() {
        try {
            List<Map<String, Object>> ofertas = canjesService.ofertasDisponibles();
            return ResponseEntity.ok(ofertas); // Devuelve la lista de ofertas en formato JSON
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Ocurrió un error al obtener las ofertas."));
        }
    }

}