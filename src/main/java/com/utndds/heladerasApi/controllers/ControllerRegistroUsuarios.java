package com.utndds.heladerasApi.controllers;

import com.utndds.heladerasApi.models.Rol.Rol;
import com.utndds.heladerasApi.services.UsuarioService;

public class ControllerRegistroUsuarios {

    private UsuarioService usuarioService;

    public ControllerRegistroUsuarios() {
        this.usuarioService = new UsuarioService();
    }

    // Método para registrar un nuevo usuario
    public String registrarUsuario(String email, String password, Rol rol) {
        return usuarioService.registrarUsuario(email, password, rol);
    }

}