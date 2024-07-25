package com.utndds.heladerasApi.models.Sistema;

import com.utndds.heladerasApi.models.Rol.Rol;
import com.utndds.heladerasApi.services.UsuarioService.PasswordHashService;

public class Usuario {
    private String email;
    private String password; // Este atributo debería ser manejado con cuidado
    private Rol rol;

    PasswordHashService passwordHashService;

    // Constructor
    public Usuario(String email, String password, Rol rol) {
        this.email = email;
        this.password = password; // Delegar el hash
        this.rol = rol;
    }

    // Getters y setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Rol getRol() {
        return rol;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

}