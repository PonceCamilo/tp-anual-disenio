package com.utndds.heladerasApi.services.UsuarioService;

import com.utndds.heladerasApi.models.Rol.Rol;
import com.utndds.heladerasApi.models.Sistema.Usuario;
import com.utndds.heladerasApi.services.Validadores.ValidadorContraseñas.ValidadorContraseña;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
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
        password = this.passwordHashService.hashPassword(password);
        // Crear el usuario
        new Usuario(email, password, rol);

        // Una vez tengamos la BD creada y se haría un insert del new Usuario

        return "Usuario registrado con éxito.";
    }

    public Usuario loginUsuario(String email, String password) {

        // Una vez tengamos la BD creada y se buscaria el usuario
        return null;
    }
}