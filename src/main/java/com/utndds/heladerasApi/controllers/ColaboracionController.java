package com.utndds.heladerasApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.utndds.heladerasApi.services.ReconocimientosExtra;
import com.utndds.heladerasApi.models.Heladera.Punto;
import com.utndds.heladerasApi.models.Rol.Colaborador;
import com.utndds.heladerasApi.services.RecomendacionDonacionService;

@RestController
@RequestMapping("/colaboraciones")
public class ColaboracionController {
    @Autowired
    private RecomendacionDonacionService recomendacionDonacionService;
    @Autowired
    private ReconocimientosExtra reconocimientosExtra;

    @PostMapping("/puntos")
    public ResponseEntity<List<Punto>> recomendarPuntosDonacion(@RequestParam double latitud,
            @RequestParam double longitud) {
        try {
            List<Punto> puntos = recomendacionDonacionService.getRecomendacionesDonaciones(latitud, longitud);
            return ResponseEntity.ok(puntos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/recomendaciones-colaboradores")
    public ResponseEntity<List<Colaborador>> recomendarColaboradores(@RequestParam double puntosReq,
            @RequestParam double viandasDonadasReq, @RequestParam int cantMaxColabs) {
        try {
            List<Colaborador> colaboradores = reconocimientosExtra.recomendarColaboradores(puntosReq, viandasDonadasReq,
                    cantMaxColabs);
            return ResponseEntity.ok(colaboradores);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    
}
