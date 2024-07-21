package com.utndds;

import com.utndds.heladerasApi.models.Usuario;
import com.utndds.heladerasApi.models.Persona.PersonaHumana;
import com.utndds.heladerasApi.models.Rol.Colaborador;
import com.utndds.heladerasApi.models.Rol.Contacto.Contacto;
import com.utndds.heladerasApi.services.PasswordHashService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.time.LocalDate;
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
        personaHumana = new PersonaHumana("Lucas", "Fernandez", LocalDate.of(1990, 5, 15));
        List<Contacto> contactos = new ArrayList<>(); // Puedes agregar instancias de Contacto si las tienes
        colaborador = new Colaborador(personaHumana, contactos, "Calle Falsa 123", new ArrayList<>());
        usuario = new Usuario("lucas.f@example.com", "ContraseñaSegura123!", personaHumana, colaborador, passwordHashService);
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