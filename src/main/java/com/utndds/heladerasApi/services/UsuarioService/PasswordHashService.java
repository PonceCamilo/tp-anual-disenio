package com.utndds.heladerasApi.services.UsuarioService;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class PasswordHashService {

    // Método para hash de contraseñas
    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    // Método para comparar contraseñas (hasheadas)
    public boolean verificarPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
