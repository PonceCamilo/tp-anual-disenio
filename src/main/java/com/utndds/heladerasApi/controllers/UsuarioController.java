package com.utndds.heladerasApi.controllers;

import com.utndds.heladerasApi.DTOs.RegistrationDTO;
import com.utndds.heladerasApi.models.ONG.Usuario;
import com.utndds.heladerasApi.models.Persona.Persona;
import com.utndds.heladerasApi.models.Persona.PersonaHumana;
import com.utndds.heladerasApi.services.UsuarioService.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registrar")
    public String registrarUsuario(@RequestBody RegistrationDTO registrationDTO) {
        try {
            // Extraer los campos del DTO
            String email = registrationDTO.getEmail();
            String password = registrationDTO.getPassword();
            String rol = registrationDTO.getRol();
            String nombre = registrationDTO.getName(); // Nombre es 'name' en el DTO
            String apellido = registrationDTO.getLastName(); // Apellido es 'lastName' en el DTO
            String direccion = registrationDTO.getAddress();
            String type = registrationDTO.getType();
            // String birthdate = registrationDTO.getBirthdate(); // Manejado despues si se
            // necesita

            // Validaciones en el Front y también en el Back
            if ("Persona Vulnerable".equalsIgnoreCase(rol) && "Persona Juridica".equalsIgnoreCase(type)) {
                throw new IllegalArgumentException(
                        "No se puede registrar una Persona Vulnerable como persona Juridica.");
            }

            Usuario usuario = null;
            Persona persona = null;
            String personaType = ""; // Variable para almacenar el tipo de persona creado

            if ("Colaborador".equalsIgnoreCase(rol) && "Humana".equalsIgnoreCase(type)) {
                usuario = new Usuario(email, password, null);
                persona = new PersonaHumana(direccion, null, nombre, apellido, null, null);
                personaType = "Persona Humana";
            } else if ("Vulnerable".equalsIgnoreCase(rol) && "Humana".equalsIgnoreCase(type)) {
                usuario = new Usuario(email, password, null);
                persona = new PersonaHumana(direccion, null, nombre, apellido, null, null);
                personaType = "Persona Humana";
            } else if ("Colaborador".equalsIgnoreCase(rol) && "Juridica".equalsIgnoreCase(type)) {
                // Implementar creación de PersonaJuridica cuando el JSON esté preparado para
                // ello
                personaType = "Persona Juridica";
            } else {
                throw new IllegalArgumentException("Tipo o rol no soportado: " + rol + " - " + type);
            }

            // Verificar que las instancias se han creado correctamente
            if (usuario != null && persona != null) {
                // Aquí puedes realizar operaciones adicionales como guardar en la base de datos
                // usuarioService.registrarUsuario(usuario, persona);

                return "Usuario y " + personaType + " registrados con éxito.";
            } else {
                throw new IllegalArgumentException("Error en la creación de las instancias de Usuario o Persona.");
            }
        } catch (IllegalArgumentException e) {
            return "Error al registrar el usuario: " + e.getMessage();
        }
    }

    @PostMapping("/login")
    public String loginUsuario(@RequestParam String email, @RequestParam String password) {
        try {
            Usuario usuario = usuarioService.loginUsuario(email, password);
            if (usuario != null) {
                return "Login exitoso.";
            }
            return "Email o contraseña incorrectos.";
        } catch (Exception e) {
            return "Error en el login: " + e.getMessage();
        }
    }
}
