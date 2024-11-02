package com.utndds.heladerasApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import com.utndds.heladerasApi.services.RolService;


@RestController
@RequestMapping("/roles")
public class RolController {

    
    @Autowired
    private RolService rolService;

    @GetMapping("/buscar-por-uuid/{uuid}")
    public ResponseEntity<?> buscarPorUUID(@PathVariable String uuid) {
        return rolService.obtenerRolPorUUID(uuid);
    }
}
