package com.utndds.heladerasApi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utndds.heladerasApi.services.Validadores.ValidadorContraseñas.ValidadorContraseña;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@RestController
public class PasswordController {
    @Autowired
    private ValidadorContraseña validadorContraseña = new ValidadorContraseña();

    @GetMapping("/validar-contraseña")
    public void validarContraseña(@RequestParam String contraseña) {
        this.validadorContraseña.validarContraseña(contraseña);
    }
}