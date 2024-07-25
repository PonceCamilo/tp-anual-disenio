package com.utndds;

import com.utndds.heladerasApi.controllers.UsuarioController;
import com.utndds.heladerasApi.models.Persona.PersonaHumana;
import com.utndds.heladerasApi.models.Rol.Colaborador;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsuarioTest {

        private PersonaHumana personaHumana;
        private Colaborador colaborador;
        private UsuarioController usuarioController;

        @BeforeEach
        public void setUp() {
                // Inicialización de los objetos necesarios
                personaHumana = new PersonaHumana(null, null, "Lucas", "Fernandez", LocalDate.of(1990, 5, 15), null);
                colaborador = new Colaborador(personaHumana, null);
                usuarioController = new UsuarioController();

                // Registrar usuario
                usuarioController.registrarUsuario("lucas.f@example.com", "ContraseñaSegura123!", colaborador);
        }

        @Test
        public void testRegistrarUsuario() {
                // Verificar el registro de usuario
                String resultado = usuarioController.registrarUsuario("lucas.f@example.com", "ContraseñaSegura123!",
                                colaborador);
                assertEquals("Usuario registrado con éxito.", resultado);
        }

        @Test
        public void testRegistrarUsuarioContraseñaInsegura() {
                // Verificar el registro de usuario
                String resultado = usuarioController.registrarUsuario("lucas.f@example.com", "12345!",
                                colaborador);
                assertEquals("La contraseña no cumple con los requisitos de seguridad.", resultado);
        }

        @Test
        public void testRegistrarUsuarioMailVacio() {
                // Verificar el registro de usuario
                String resultado = usuarioController.registrarUsuario(null, "ContraseñaSegura123!", colaborador);
                assertEquals("El email no puede estar vacío.", resultado);
        }
}