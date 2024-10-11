package com.utndds.heladerasApi.services.Colaboraciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import com.utndds.heladerasApi.DTOs.PersonaVulnerableDTO;
import com.utndds.heladerasApi.models.Colaboraciones.RegistroPersonaVulnerable;
import com.utndds.heladerasApi.models.Rol.Colaborador;
import com.utndds.heladerasApi.models.Rol.PersonaVulnerable;
import com.utndds.heladerasApi.models.Tarjetas.TarjetaPersVuln;
import com.utndds.heladerasApi.repositories.ColaboradorRepository;
import com.utndds.heladerasApi.repositories.PersonaVulnerableRepository;
import com.utndds.heladerasApi.repositories.ColaboracionesRepositories.RegistroPersonaVulnerableRepository;
import com.utndds.heladerasApi.repositories.TarjetasRepositories.TarjetaPersVulRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RegistroPersVulnService {

    @Autowired
    private PersonaVulnerableRepository personaVulnerableRepository;

    @Autowired
    private TarjetaPersVulRepository tarjetaPersVulnRepository;

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @Autowired
    private RegistroPersonaVulnerableRepository registroPersonaVulnerableRepository;

    // Método que recibe los datos del formulario y crea la persona vulnerable y su
    // tarjeta
    public void crearPersonaVulnerable(PersonaVulnerableDTO personaDTO, Long colaborador_id) {
        // Crear la persona vulnerable con los datos del formulario
        PersonaVulnerable persona = new PersonaVulnerable();
        persona.setSituacionCalle(personaDTO.getSituacionCalle());
        persona.setCantMenoresAcargo(personaDTO.getCantMenoresAcargo());
        persona.setFechaRegistro(LocalDate.now()); // Fecha actual

        // Crear la tarjeta asociada a esa persona
        TarjetaPersVuln tarjeta = new TarjetaPersVuln(persona);

        // Asignar la tarjeta a la persona vulnerable
        persona.setTarjeta(tarjeta);
        // Guardar la relación
        tarjetaPersVulnRepository.save(tarjeta);
        personaVulnerableRepository.save(persona);

        Colaborador colaborador = colaboradorRepository.findById(colaborador_id)
                .orElseThrow(() -> new EntityNotFoundException("Colaborador no encontrado con id " + colaborador_id));

        RegistroPersonaVulnerable registro = new RegistroPersonaVulnerable(colaborador, persona, tarjeta);
        registroPersonaVulnerableRepository.save(registro);
    }
}