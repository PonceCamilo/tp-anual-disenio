package com.utndds.heladerasApi.controllers;

import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.DTOs.RecomendacionDTO;
import com.utndds.heladerasApi.DTOs.AreaRecomendacionDTO;
import com.utndds.heladerasApi.services.HeladeraService;
import com.utndds.heladerasApi.services.RecomendacionPuntosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/heladeras")
public class HeladeraController {

    @Autowired
    private RecomendacionPuntosService recomendacionService;
    @Autowired
    private HeladeraService heladeraService;

    @PostMapping("/recomendarPuntos")
    public List<RecomendacionDTO> recomendarPuntosColocacion(@RequestBody AreaRecomendacionDTO data) {
        return recomendacionService.getRecomendaciones(data.getLatitud(), data.getLongitud(), data.getRadio());
    }

    @PostMapping("/agregar")
    public ResponseEntity<String> agregarHeladera(@RequestBody Heladera heladera) {
        heladeraService.agregarHeladera(heladera);
        return ResponseEntity.ok("Heladera agregada exitosamente");
    }
}
