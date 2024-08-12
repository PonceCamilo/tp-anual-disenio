package com.utndds.heladerasApi.controllers;

import com.utndds.heladerasApi.models.Heladera.Punto;
import com.utndds.heladerasApi.services.RecomendacionPuntosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/heladeras")
public class HeladeraController {

    @Autowired
    private RecomendacionPuntosService recomendacionService;

    @GetMapping("/recomendarPuntos")
    public List<Punto> recomendarPuntosColocacion(@RequestParam double latitud,
            @RequestParam double longitud,
            @RequestParam double radio) {
        return recomendacionService.getRecomendaciones(latitud, longitud, radio);
    }

    public static void main(String[] args) {
        HeladeraController controlador = new HeladeraController();
        System.out.println(controlador.recomendarPuntosColocacion(1, 1, 1));
    }
}
