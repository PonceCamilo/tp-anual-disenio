package com.utndds.heladerasApi.services;

import com.utndds.heladerasApi.models.Rol.Rol;
import com.utndds.heladerasApi.models.Sistema.Usuario;
import com.utndds.heladerasApi.services.Validadores.ValidadorContraseñas.ValidadorContraseña;

public class UsuarioService {

    private ValidadorContraseña validadorContraseña;
    private PasswordHashService passwordHashService;

    public UsuarioService() {
        this.validadorContraseña = new ValidadorContraseña();
        this.passwordHashService = new PasswordHashService();
    }

    // Método para registrar un nuevo usuario
    public String registrarUsuario(String email, String password, Rol rol) {

        if (email == null || email.isEmpty()) {
            return "El email no puede estar vacío.";
        }

        // Validar la contraseña
        if (!validadorContraseña.validarContraseña(password)) {
            return "La contraseña no cumple con los requisitos de seguridad.";
        }

        // Crear el usuario
        new Usuario(email, password, rol, passwordHashService);

        // Una vez tengamos la BD creada y se haría un insert del new Usuario

        return "Usuario registrado con éxito.";
    }
}