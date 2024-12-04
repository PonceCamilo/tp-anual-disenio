package com.utndds.heladerasApi.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.utndds.heladerasApi.models.Rol.Colaborador;
import com.utndds.heladerasApi.repositories.ColaboradorRepository;
import com.utndds.heladerasApi.services.RolService;
import com.utndds.heladerasApi.services.ABM.UserService;

@RestController
@RequestMapping("/roles")
public class RolController {

    @Autowired
    private RolService rolService;
    @Autowired
    private ColaboradorRepository colaboracionRepository;
    @Autowired
    private UserService userService;

    @GetMapping("/buscar-por-uuid/{uuid}")
    public ResponseEntity<?> buscarPorUUID(@PathVariable String uuid) {
        return rolService.obtenerRolPorUUID(uuid);
    }

    @GetMapping("/buscar-por-id/{id}")
    public Optional<Colaborador> buscarPorID(@RequestParam Long id) {
        return colaboracionRepository.findById(id);
    }
    
    @PostMapping("/crear-colaborador")
    public ResponseEntity<String> crearColaborador(
        @RequestParam("colaboradorUUID") String uuid,
        @RequestParam("id") String id )
    {
    try {
        System.out.println("Creando colaborador...------------- uuid = " + uuid + " id = " + id);
        userService.crearColaborador(uuid, id);
        return ResponseEntity.ok("Colaborador creado exitosamente.");
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al crear el colaborador: " + e.getMessage());
    }
}
}
