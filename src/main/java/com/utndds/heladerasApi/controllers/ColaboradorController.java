package com.utndds.heladerasApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utndds.heladerasApi.models.Rol.Colaborador;
import com.utndds.heladerasApi.services.CalculadoraPuntosService;

@RestController
@RequestMapping("/colaboradores")
public class ColaboradorController {
    @Autowired
    private CalculadoraPuntosService calculadoraPuntosService;

    @PostMapping("/puntos")
    public ResponseEntity<Double> calcularPuntos(@RequestBody Colaborador colaborador) {
        try {
            double puntos = calculadoraPuntosService.calcularPuntos(colaborador);
            return ResponseEntity.ok(puntos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}