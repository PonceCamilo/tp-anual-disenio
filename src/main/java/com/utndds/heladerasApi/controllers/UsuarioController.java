package com.utndds.heladerasApi.controllers;

import com.utndds.heladerasApi.models.Rol.Rol;
import com.utndds.heladerasApi.models.Sistema.Usuario;
import com.utndds.heladerasApi.services.UsuarioService.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService = new UsuarioService();

    @PostMapping("/registro")
    public String registrarUsuario(@RequestParam String email, @RequestParam String password, @RequestParam Rol rol) {
        return usuarioService.registrarUsuario(email, password, rol);
    }

    @PostMapping("/login")
    public String loginUsuario(@RequestParam String email, @RequestParam String password) {
        Usuario usuario = usuarioService.loginUsuario(email, password);
        if (usuario != null) {
            return "Login exitoso.";
        }
        return "Email o contraseña incorrectos.";
    }
}