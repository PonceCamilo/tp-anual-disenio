package com.utndds.heladerasApi.controllers;

import com.utndds.heladerasApi.DTOs.AreaRecomendacionDTO;
import com.utndds.heladerasApi.DTOs.HeladeraDTO;
import com.utndds.heladerasApi.DTOs.RecomendacionDTO;
import com.utndds.heladerasApi.services.RecomendacionPuntosService;
import com.utndds.heladerasApi.services.ABM.HeladeraService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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
    public ResponseEntity<String> agregarHeladera(@RequestBody HeladeraDTO heladeraDTO) {
        heladeraService.crearHeladera(heladeraDTO);
        return ResponseEntity.ok("Heladera agregada exitosamente");
    }
}
