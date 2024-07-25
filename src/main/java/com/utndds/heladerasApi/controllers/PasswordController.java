package com.utndds.heladerasApi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utndds.heladerasApi.services.Validadores.ValidadorContraseñas.ValidadorContraseña;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@RestController
public class PasswordController {
    @GetMapping("/validar-contraseña")

    public void validarContraseña(@RequestParam String contraseña) {
        new ValidadorContraseña().validarContraseña(contraseña);
    }
}