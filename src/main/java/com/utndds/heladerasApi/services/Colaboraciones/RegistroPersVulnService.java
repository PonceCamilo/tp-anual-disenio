package com.utndds.heladerasApi.services.Colaboraciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import com.utndds.heladerasApi.DTOs.PersonaVulnerableDTO;
import com.utndds.heladerasApi.models.Colaboraciones.RegistroPersonaVulnerable;
import com.utndds.heladerasApi.models.Persona.PersonaHumana;
import com.utndds.heladerasApi.models.Rol.Colaborador;
import com.utndds.heladerasApi.models.Rol.PersonaVulnerable;
import com.utndds.heladerasApi.models.Tarjetas.TarjetaPersVuln;
import com.utndds.heladerasApi.repositories.ColaboradorRepository;
import com.utndds.heladerasApi.repositories.PersonaRepository;
import com.utndds.heladerasApi.repositories.PersonaVulnerableRepository;
import com.utndds.heladerasApi.repositories.ColaboracionesRepositories.RegistroPersonaVulnerableRepository;
import com.utndds.heladerasApi.repositories.TarjetasRepositories.TarjetaPersVulRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class RegistroPersVulnService {

    @Autowired
    private PersonaVulnerableRepository personaVulnerableRepository;

    @Autowired
    private TarjetaPersVulRepository tarjetaPersVulnRepository;

    @Autowired
    private ColaboradorRepository colaboradorRepository;
    @Autowired
    private PersonaRepository personaHumanaRepository;

    @Autowired
    private RegistroPersonaVulnerableRepository registroPersonaVulnerableRepository;

    // Método que recibe los datos del formulario y crea la persona vulnerable y su
    // tarjeta
    @Transactional
    public void crearPersonaVulnerable(PersonaVulnerableDTO personaDTO, String colaboradorUUID) {
        // Crear y guardar PersonaHumana
        PersonaHumana personaHumana = new PersonaHumana();
        personaHumana.setNombre(personaDTO.getNombre());
        personaHumana.setApellido(personaDTO.getApellido());
        personaHumana.setFechaDeNacimiento(personaDTO.getFechaNacimiento());
        personaHumana.setDireccion(personaDTO.getDireccion());

        personaHumanaRepository.save(personaHumana);

        // Crear la persona vulnerable con los datos del formulario
        PersonaVulnerable persona = new PersonaVulnerable();
        persona.setPersona(personaHumana);
        persona.setSituacionCalle(personaDTO.getSituacionCalle());
        persona.setCantMenoresAcargo(personaDTO.getCantMenoresAcargo());
        persona.setFechaRegistro(LocalDate.now()); // Fecha actual
        // Guardar PersonaVulnerable
        personaVulnerableRepository.save(persona);

        // Crear la tarjeta asociada a esa persona
        TarjetaPersVuln tarjeta = new TarjetaPersVuln(persona);
        // Asignar la tarjeta a la persona vulnerable
        persona.setTarjeta(tarjeta);
        // Guardar TarjetaPersVuln
        tarjetaPersVulnRepository.save(tarjeta);

        // Obtener el colaborador por UUID
        Colaborador colaborador = colaboradorRepository.findByUUID(colaboradorUUID)
                .orElseThrow(
                        () -> new EntityNotFoundException("Colaborador no encontrado con uuid " + colaboradorUUID));

        // Crear y guardar el registro
        RegistroPersonaVulnerable registro = new RegistroPersonaVulnerable(colaborador, persona, tarjeta);
        registroPersonaVulnerableRepository.save(registro);
    }
}