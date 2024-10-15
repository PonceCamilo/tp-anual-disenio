package com.utndds.heladerasApi.controllers;

import com.utndds.heladerasApi.DTOs.TecnicoDTO;
import com.utndds.heladerasApi.services.ABM.TecnicoService;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tecnicos")
public class TecnicoController {

    @Autowired
    private TecnicoService tecnicoService;

    // Alta: Agregar un nuevo técnico
    @PostMapping("/agregar")
    public ResponseEntity<String> agregarTecnico(@RequestBody TecnicoDTO tecnicoDTO) {
        tecnicoService.crearTecnico(tecnicoDTO);
        return ResponseEntity.ok("Técnico agregado exitosamente.");
    }

    // Baja: Eliminar un técnico por ID
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarTecnico(@PathVariable Long id) {
        try {
            tecnicoService.eliminarTecnico(id);
            return ResponseEntity.ok("Técnico eliminado exitosamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Modificación: Actualizar un técnico existente por ID
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizarTecnico(@PathVariable Long id, @RequestBody TecnicoDTO tecnicoDTO) {
        try {
            tecnicoService.actualizarTecnico(id, tecnicoDTO);
            return ResponseEntity.ok("Técnico actualizado exitosamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}