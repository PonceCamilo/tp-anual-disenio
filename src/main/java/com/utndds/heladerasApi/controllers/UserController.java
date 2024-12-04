package com.utndds.heladerasApi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.utndds.heladerasApi.DTOs.PersonaHumanaDTO;
import com.utndds.heladerasApi.DTOs.PersonaJuridicaDTO;
import com.utndds.heladerasApi.services.ABM.UserService;
import org.springframework.beans.factory.annotation.Autowired;
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService UserService;

    @PostMapping("/personaHumana")
    public ResponseEntity<String> personaHumana(@RequestBody PersonaHumanaDTO personaHumanaDTO) {
        Long id = UserService.crearPersonaHumana(personaHumanaDTO);
        return ResponseEntity.ok("Alta exitosa. ID: " + id);
    }

    @PostMapping("/personaJuridica")
    public ResponseEntity<String> personaJuridica(@RequestBody PersonaJuridicaDTO personaJuridicaDTO) {
        Long id = UserService.crearPersonaJuridica(personaJuridicaDTO);
        return ResponseEntity.ok("Alta exitosa." + id);
    }
    
    
}
