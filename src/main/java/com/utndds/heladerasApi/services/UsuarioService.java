package com.utndds.heladerasApi.services;

import com.utndds.heladerasApi.models.Usuario;
import com.utndds.heladerasApi.models.Persona.Persona;
import com.utndds.heladerasApi.models.Rol.Rol;
import com.utndds.heladerasApi.models.Validadores.ValidadorContraseñas.ValidadorContraseña;

public class UsuarioService {

    private ValidadorContraseña validadorContraseña;
    private PasswordHashService passwordHashService;
    
    public UsuarioService() {
        this.validadorContraseña = new ValidadorContraseña();
        this.passwordHashService = new PasswordHashService();
    }

    // Método para registrar un nuevo usuario
    public String registrarUsuario(String email, String password, Persona persona, Rol rol) {
        
        if (email == null || email.isEmpty()) {
            return "El email no puede estar vacío.";
        }
        
        // Validar la contraseña
        if (!validadorContraseña.validarContraseña(password)) {
            return "La contraseña no cumple con los requisitos de seguridad.";
        }
        
        // Crear el usuario
        Usuario usuario = new Usuario(email, password, persona, rol, passwordHashService);
        
        //Dice que no se usa , pero se usaria una vez tengamos la BD creada y se haría un insert

        return "Usuario registrado con éxito.";
    }
}