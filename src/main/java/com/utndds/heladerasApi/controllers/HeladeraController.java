package com.utndds.heladerasApi.controllers;

import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Heladera.Punto;
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

    @GetMapping("/recomendarPuntos")
    public List<Punto> recomendarPuntosColocacion(@RequestParam double latitud,
            @RequestParam double longitud,
            @RequestParam double radio) {
        return recomendacionService.getRecomendaciones(latitud, longitud, radio);
    }

    @PostMapping("/agregar")
    public ResponseEntity<String> agregarHeladera(@RequestBody Heladera heladera) {
        heladeraService.agregarHeladera(heladera);
        return ResponseEntity.ok("Heladera agregada exitosamente");
    }
}
