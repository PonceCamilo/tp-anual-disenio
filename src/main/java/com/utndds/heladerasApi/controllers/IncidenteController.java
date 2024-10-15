package com.utndds.heladerasApi.controllers;

import com.utndds.heladerasApi.DTOs.FallaTecnicaDTO;
import com.utndds.heladerasApi.DTOs.VisitaTecnicoDTO;
import com.utndds.heladerasApi.services.IncidenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/incidentes")
public class IncidenteController {

    @Autowired
    private IncidenteService incidenteService;

    @PostMapping("/reportarFallaTecnica")
    public ResponseEntity<String> reportarFallaTecnica(@RequestParam Long colaboradorId,
            @RequestBody FallaTecnicaDTO fallaTecnicaDTO) {
        incidenteService.reportarFallaTecnica(colaboradorId, fallaTecnicaDTO);
        return ResponseEntity.ok("Falla Técnica reportada con éxito");
    }

    @PostMapping("/registrarVisita/{tecnicoId}")
    public ResponseEntity<String> registrarVisita(@PathVariable Long tecnicoId,
            @RequestBody VisitaTecnicoDTO visitaDTO) {
        incidenteService.registrarVisita(tecnicoId, visitaDTO);
        return ResponseEntity.ok("Visita del técnico registrada con éxito");
    }

    @PostMapping("/alerta/{heladeraId}")
    public ResponseEntity<String> generarAlerta(@PathVariable Long heladeraId, @RequestParam String tipoAlerta) {
        incidenteService.generarAlerta(heladeraId, tipoAlerta);
        return ResponseEntity.ok("Alerta generada con éxito");
    }

}