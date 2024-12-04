package com.utndds.heladerasApi.controllers;

import com.utndds.heladerasApi.DTOs.FallaTecnicaDTO;
import com.utndds.heladerasApi.DTOs.VisitaTecnicoDTO;
import com.utndds.heladerasApi.services.IncidenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/incidentes")
public class IncidenteController {

    @Autowired
    private IncidenteService incidenteService;

    @PreAuthorize("hasAuthority('SCOPE_ROLE_COLLABORATOR')")
    @PostMapping("/reportarFallaTecnica")
    public ResponseEntity<String> reportarFallaTecnica(@RequestParam String colaboradorUUID,
            @RequestBody FallaTecnicaDTO fallaTecnicaDTO) {
        incidenteService.reportarFallaTecnica(colaboradorUUID, fallaTecnicaDTO);
        return ResponseEntity.ok("Falla Técnica reportada con éxito");
    }

    @PreAuthorize("hasAuthority('SCOPE_ROLE_TECHNICAL')")
    @PostMapping("/registrarVisita")
    public ResponseEntity<String> registrarVisita(@RequestParam String tecnicoUUID,
            @RequestBody VisitaTecnicoDTO visitaDTO) {
        incidenteService.registrarVisita(tecnicoUUID, visitaDTO);
        return ResponseEntity.ok("Visita del técnico registrada con éxito");
    }

    @PostMapping("/alerta/{heladeraId}")
    public ResponseEntity<String> generarAlerta(@PathVariable Long heladeraId, @RequestParam String tipoAlerta) {
        incidenteService.generarAlerta(heladeraId, tipoAlerta);
        return ResponseEntity.ok("Alerta generada con éxito");
    }

}