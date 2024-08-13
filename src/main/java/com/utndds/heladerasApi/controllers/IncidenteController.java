package com.utndds.heladerasApi.controllers;

import com.utndds.heladerasApi.models.Heladera.Incidentes.*;
import com.utndds.heladerasApi.services.IncidenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/incidentes")
public class IncidenteController {

    @Autowired
    private IncidenteService incidenteService;

    @PostMapping("/reportar")
    public void reportarIncidente(@RequestBody Incidente incidente) {
        incidenteService.reportarIncidente(incidente);
    }

    @PostMapping("/registrarVisita")
    public void registrarVisita(@RequestBody VisitaTecnico visita) {
        incidenteService.registrarVisita(visita);
    }
}