package com.utndds.heladerasApi.models.Sistema;

import com.utndds.heladerasApi.models.Rol.Rol;
import com.utndds.heladerasApi.services.PasswordHashService;

public class Usuario {
    private String email;
    private String password; // Este atributo debería ser manejado con cuidado
    private Rol rol;

    private PasswordHashService passwordHashService;

    // Constructor
    public Usuario(String email, String password, Rol rol, PasswordHashService passwordHashService) {
        this.email = email;
        this.passwordHashService = passwordHashService;
        this.password = passwordHashService.hashPassword(password); // Delegar el hash
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

    // Método para comparar contraseñas (hasheadas) se usa para el logeo del usuario
    public boolean verificarPassword(String password) {
        return passwordHashService.verificarPassword(password, this.password);
    }
}