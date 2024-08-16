package com.utndds.heladerasApi.controllers;


import com.utndds.heladerasApi.DTOs.RegistrationDTO;
import com.utndds.heladerasApi.models.ONG.Usuario;
import com.utndds.heladerasApi.models.Persona.Persona;
import com.utndds.heladerasApi.models.Persona.PersonaHumana;
import com.utndds.heladerasApi.models.Persona.PersonaJuridica;
import com.utndds.heladerasApi.models.Persona.Contacto.Contacto;
import com.utndds.heladerasApi.models.Persona.Contacto.Email;
import com.utndds.heladerasApi.services.UsuarioService.UsuarioService;
import com.utndds.heladerasApi.models.Persona.Contacto.Telefono;
import com.utndds.heladerasApi.models.Persona.Contacto.Whatsapp;
import com.utndds.heladerasApi.services.UsuarioService.PasswordHashService;
import com.utndds.heladerasApi.services.Validadores.ValidadorContraseñas.ValidadorContraseña;
import com.utndds.heladerasApi.models.Rol.Colaborador;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ValidadorContraseña validadorContraseña;
    @Autowired
    private PasswordHashService passwordHashService;

    @PostMapping("/registrar")
    public String registrarUsuario(@RequestBody RegistrationDTO registrationDTO) {
        System.out.println("Datos recibidos:");
        System.out.println("Tipo de persona: " + registrationDTO.getType());
        System.out.println("Nombre: " + registrationDTO.getName());
        System.out.println("Apellido: " + registrationDTO.getLastName());
        System.out.println("Fecha de nacimiento: " + registrationDTO.getBirthdate());
        System.out.println("Dirección: " + registrationDTO.getAddress());
        System.out.println("Email: " + registrationDTO.getEmail());
        System.out.println("Teléfono: " + registrationDTO.getTelefono());
        System.out.println("Whatsapp: " + registrationDTO.getWhatsapp());
        System.out.println("Contraseña: " + registrationDTO.getPassword());
        System.out.println("Rol: " + registrationDTO.getRol());
        System.out.println("Razón Social: " + registrationDTO.getCompanyName());
        System.out.println("Rubro: " + registrationDTO.getCategory());
        System.out.println("Tipo de organización: " + registrationDTO.getOrganizationType());
        System.out.println("email contacto: " + registrationDTO.getEmailContact());
        try {
            String password = registrationDTO.getPassword();
            if (!validadorContraseña.validarContraseña(password)) {
                return "Error: Contraseña no cumple con los requisitos mínimos.";
            }
            
            String hashedPassword = passwordHashService.hashPassword(password);
            String type = registrationDTO.getType(); // Persona Humana o Juridica

            if ("humana".equalsIgnoreCase(type)) {
                return registrarHumano(registrationDTO, hashedPassword);
            } else if ("juridica".equalsIgnoreCase(type)) {
                return registrarJuridico(registrationDTO, hashedPassword);
            } else {
                return "Error: Tipo de persona no reconocido.";
            }
        } catch (DateTimeParseException e) {
            return "Error al registrar el usuario: formato de fecha incorrecto.";
        } catch (IllegalArgumentException e) {
            return "Error al registrar el usuario: " + e.getMessage();
        }
    }

    private String registrarHumano(RegistrationDTO registrationDTO, String hashedPassword) {
        PersonaHumana personaHumana = setHumano(registrationDTO);
        return registrarColaborador(personaHumana, registrationDTO.getRol(), registrationDTO.getEmail(), hashedPassword);
    }

    private String registrarJuridico(RegistrationDTO registrationDTO, String hashedPassword) {
        PersonaJuridica personaJuridica = setJuridico(registrationDTO);
        return registrarColaborador(personaJuridica, registrationDTO.getRol(), registrationDTO.getEmail(), hashedPassword);
    }

    private PersonaHumana setHumano(RegistrationDTO registrationDTO) {
        // Datos obligatorios
        String nombre = registrationDTO.getName();
        String apellido = registrationDTO.getLastName();
        Email email = new Email(registrationDTO.getEmail());
    
        // Datos opcionales
        String direccion = registrationDTO.getAddress();
        String whatsapp = registrationDTO.getWhatsapp();
        String telefono = registrationDTO.getTelefono();
        String nacimiento = registrationDTO.getBirthdate();
        String emailContacto = registrationDTO.getEmailContact();
        LocalDate fechaDeNacimiento = null;
    
        
        if (nacimiento != null && !nacimiento.equals("null")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            fechaDeNacimiento = LocalDate.parse(nacimiento, formatter);
        }
    
        
        List<Contacto> mediosContacto = new ArrayList<>();
        if (whatsapp != null && !whatsapp.isEmpty()) {
            mediosContacto.add(new Whatsapp(whatsapp));
        }
        if (telefono != null && !telefono.isEmpty()) {
            mediosContacto.add(new Telefono(telefono));
        }
        if (emailContacto != null && !emailContacto.isEmpty()) {
            mediosContacto.add(new Email(emailContacto));
        }
        if (mediosContacto.isEmpty()) {  // Si no hay whatsapp ni teléfono, agregar el email de registro
            mediosContacto.add(email);
        }
    
        if (nombre == null || nombre.isEmpty() || 
            apellido == null || apellido.isEmpty() || 
            mediosContacto.isEmpty()) {
            throw new IllegalArgumentException("Nombre, apellido y al menos un medio de contacto son obligatorios.");
        }
    
        return new PersonaHumana(direccion, mediosContacto, nombre, apellido, fechaDeNacimiento, null);
    }
    

    private PersonaJuridica setJuridico(RegistrationDTO registrationDTO) {
        // Datos obligatorios
        String razonSocial = registrationDTO.getCompanyName();
        String rubro = registrationDTO.getCategory();
        String tipoOrganizacion = registrationDTO.getOrganizationType();
        Email email = new Email(registrationDTO.getEmail());
        // Datos opcionales
        String direccion = registrationDTO.getAddress();
        String whatsapp = registrationDTO.getWhatsapp();
        String telefono = registrationDTO.getTelefono();
        String emailContacto = registrationDTO.getEmailContact();
    
        // Lista de medios de contacto
        List<Contacto> mediosContacto = new ArrayList<>();
        if (whatsapp != null && !whatsapp.isEmpty()) {
            mediosContacto.add(new Whatsapp(whatsapp));
        }
        if (telefono != null && !telefono.isEmpty()) {
            mediosContacto.add(new Telefono(telefono));
        }
        if (emailContacto != null && !emailContacto.isEmpty()) {
            mediosContacto.add(new Email(emailContacto));
        }
        if (mediosContacto.isEmpty()) {  // Si no hay whatsapp ni teléfono, agregar el email de registro
            mediosContacto.add(email);
        }
    
        // Validaciones
        if (razonSocial == null || razonSocial.isEmpty() || 
            rubro == null || rubro.isEmpty() || 
            tipoOrganizacion == null || tipoOrganizacion.isEmpty() || 
            mediosContacto.isEmpty()) {
            throw new IllegalArgumentException("Razón social, rubro, tipo de organización y al menos un medio de contacto son obligatorios.");
        }
    return new PersonaJuridica(direccion, mediosContacto, razonSocial, tipoOrganizacion, rubro);
    }
    
    
    

    private String registrarColaborador(Persona persona, String rol, String email, String hashedPassword) {
        if ("Colaborador".equalsIgnoreCase(rol)) {
            Colaborador colaborador = new Colaborador(persona, new ArrayList<>());
            Usuario nuevoUsuario = new Usuario(email, hashedPassword, colaborador);

            // Guardar el nuevo usuario en la base de datos
            //usuarioService.guardarUsuario(nuevoUsuario);

            return "Usuario colaborador creado exitosamente.";
        }
        // Otros roles pueden ser manejados aquí...
        return "Error al registrar el Usuario.";
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
