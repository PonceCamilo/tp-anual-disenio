package com.utndds.heladerasApi.controllers;

import com.utndds.heladerasApi.models.Heladera.Ubicacion;
import com.utndds.heladerasApi.services.RecomendacionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/heladeras")
public class HeladeraController {

    @Autowired
    private RecomendacionService recomendacionService;

    @PostMapping("/recomendarPuntos")
    public List<Ubicacion> recomendarPuntosColocacion(@RequestParam double latitud, @RequestParam double longitud,
            @RequestParam double radio) {
        return recomendacionService.getRecomendaciones(latitud, longitud, radio);
    }

}