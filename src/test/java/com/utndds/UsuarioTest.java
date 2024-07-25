package com.utndds;

import com.utndds.heladerasApi.models.Persona.PersonaHumana;
import com.utndds.heladerasApi.models.Persona.Contacto.Contacto;
import com.utndds.heladerasApi.models.Rol.Colaborador;
import com.utndds.heladerasApi.models.Sistema.Usuario;
import com.utndds.heladerasApi.services.PasswordHashService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class UsuarioTest {

    private PasswordHashService passwordHashService;
    private PersonaHumana personaHumana;
    private Colaborador colaborador;
    private Usuario usuario;

    @BeforeEach
    public void setUp() {
        passwordHashService = new PasswordHashService();
        List<Contacto> contactos = new ArrayList<>();
        personaHumana = new PersonaHumana(null, contactos, "Lucas", "Fernandez", LocalDate.of(1990, 5, 15), null);
        colaborador = new Colaborador(personaHumana, null);
        usuario = new Usuario("lucas.f@example.com", "ContraseñaSegura123!", colaborador, passwordHashService);
    }

    @Test
    public void testEmail() {
        // Verificar el email
        assertTrue(usuario.getEmail().equals("lucas.f@example.com"));
    }

    @Test
    public void testPasswordCorrecta() {
        // Verificar la contraseña (usando un password correcto)
        assertTrue(usuario.verificarPassword("ContraseñaSegura123!"));
    }

    @Test
    public void testPasswordIncorrecta() {
        // Verificar la contraseña (usando un password incorrecto)
        assertFalse(usuario.verificarPassword("ContraseñaIncorrecta!"));
    }
}