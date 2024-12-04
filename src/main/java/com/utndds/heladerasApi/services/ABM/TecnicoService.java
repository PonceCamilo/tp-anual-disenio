package com.utndds.heladerasApi.services.ABM;

import com.utndds.heladerasApi.DTOs.TecnicoDTO;
import com.utndds.heladerasApi.models.Persona.Documento;
import com.utndds.heladerasApi.models.Persona.PersonaHumana;
import com.utndds.heladerasApi.models.Rol.Tecnico.Tecnico;
import com.utndds.heladerasApi.repositories.TecnicoRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    // Alta: Crear un nuevo técnico desde el DTO
    public void crearTecnico(TecnicoDTO tecnicoDTO) {
        // Crear PersonaHumana asociada al técnico
        PersonaHumana persona = new PersonaHumana();
        persona.setNombre(tecnicoDTO.getNombre());
        persona.setApellido(tecnicoDTO.getApellido());

        Documento documento = new Documento();
        documento.setTipo(tecnicoDTO.getTipoDocumento());
        documento.setNumero(tecnicoDTO.getNumeroDocumento());
        persona.setDocumento(documento);

        // Crear el técnico y asociar la persona
        Tecnico tecnico = new Tecnico();
        tecnico.setPersona(persona);
        tecnico.setCuil(tecnicoDTO.getCuil());

        // Asignar el área de cobertura directamente desde el DTO
        tecnico.setAreaCobertura(tecnicoDTO.getAreaCobertura());

        // Guardar en la base de datos
        tecnicoRepository.save(tecnico);
    }

    // Baja: Eliminar un técnico por ID
    public void eliminarTecnico(Long id) {
        tecnicoRepository.deleteById(id);
    }

    // Modificación: Actualizar un técnico existente desde el DTO
    public void actualizarTecnico(Long id, TecnicoDTO tecnicoDTO) {
        Optional<Tecnico> tecnicoOptional = tecnicoRepository.findById(id);
        if (tecnicoOptional.isPresent()) {
            Tecnico tecnico = tecnicoOptional.get();
            PersonaHumana persona = (PersonaHumana) tecnico.getPersona();

            // Actualizar datos de la persona
            persona.setNombre(tecnicoDTO.getNombre());
            persona.setApellido(tecnicoDTO.getApellido());

            Documento documento = persona.getDocumento();
            documento.setTipo(tecnicoDTO.getTipoDocumento());
            documento.setNumero(tecnicoDTO.getNumeroDocumento());

            // Actualizar el CUIL
            tecnico.setCuil(tecnicoDTO.getCuil());

            // Actualizar el área de cobertura directamente desde el DTO
            tecnico.setAreaCobertura(tecnicoDTO.getAreaCobertura());

            // Guardar los cambios en la base de datos
            tecnicoRepository.save(tecnico);
        } else {
            throw new EntityNotFoundException("Técnico no encontrado con el ID: " + id);
        }
    }
}