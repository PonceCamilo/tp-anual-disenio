package com.utndds.ABMtests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.utndds.heladerasApi.HeladerasApiApplication;
import com.utndds.heladerasApi.DTOs.TecnicoDTO;
import com.utndds.heladerasApi.models.Persona.Documento;
import com.utndds.heladerasApi.models.Persona.PersonaHumana;
import com.utndds.heladerasApi.models.Rol.Tecnico.AreaCobertura;
import com.utndds.heladerasApi.models.Rol.Tecnico.Tecnico;
import com.utndds.heladerasApi.repositories.TecnicoRepository;
import com.utndds.heladerasApi.services.ABM.TecnicoService;

import jakarta.persistence.EntityNotFoundException;

import java.util.Optional;

@SpringBootTest(classes = HeladerasApiApplication.class)
public class TecnicoServiceTest {

    @Autowired
    private TecnicoService tecnicoService;

    @MockBean
    private TecnicoRepository tecnicoRepository;

    @Test
    public void testCrearTecnico() {
        // Crear un DTO de ejemplo
        TecnicoDTO tecnicoDTO = new TecnicoDTO();
        tecnicoDTO.setNombre("Juan");
        tecnicoDTO.setApellido("Perez");
        tecnicoDTO.setTipoDocumento("DNI");
        tecnicoDTO.setNumeroDocumento("12345678");
        tecnicoDTO.setCuil("20-12345678-9");
        tecnicoDTO.setAreaCobertura(new AreaCobertura());

        // Simulación de guardado en repositorio
        when(tecnicoRepository.save(any(Tecnico.class))).thenReturn(new Tecnico());

        // Ejecutar el servicio
        tecnicoService.crearTecnico(tecnicoDTO);

        // Verificar que el método de guardado fue llamado una vez
        verify(tecnicoRepository, times(1)).save(any(Tecnico.class));
    }

    @Test
    public void testEliminarTecnico() {
        Long idTecnico = 1L;

        // Ejecutar el servicio
        tecnicoService.eliminarTecnico(idTecnico);

        // Verificar que se eliminó por ID
        verify(tecnicoRepository, times(1)).deleteById(idTecnico);
    }

    @Test
    public void testActualizarTecnico_Exito() {
        Long idTecnico = 1L;

        // Crear un DTO de ejemplo para la actualización
        TecnicoDTO tecnicoDTO = new TecnicoDTO();
        tecnicoDTO.setNombre("Carlos");
        tecnicoDTO.setApellido("Lopez");
        tecnicoDTO.setTipoDocumento("DNI");
        tecnicoDTO.setNumeroDocumento("87654321");
        tecnicoDTO.setCuil("20-87654321-0");
        tecnicoDTO.setAreaCobertura(new AreaCobertura());

        // Simulación de técnico existente en la base de datos
        Tecnico tecnicoExistente = new Tecnico();
        PersonaHumana persona = new PersonaHumana();
        persona.setDocumento(new Documento("DNI", "12345678"));
        tecnicoExistente.setPersona(persona);
        when(tecnicoRepository.findById(idTecnico)).thenReturn(Optional.of(tecnicoExistente));

        // Ejecutar el servicio
        tecnicoService.actualizarTecnico(idTecnico, tecnicoDTO);

        // Verificar que el técnico fue actualizado y guardado
        verify(tecnicoRepository, times(1)).save(tecnicoExistente);
        assertEquals("Carlos", persona.getNombre());
        assertEquals("Lopez", persona.getApellido());
        assertEquals("DNI", persona.getDocumento().getTipo());
        assertEquals("87654321", persona.getDocumento().getNumero());
    }

    @Test
    public void testActualizarTecnico_NoEncontrado() {
        Long idTecnico = 1L;

        // Simulación de técnico no encontrado
        when(tecnicoRepository.findById(idTecnico)).thenReturn(Optional.empty());

        // Verificar que lanza la excepción correcta
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            tecnicoService.actualizarTecnico(idTecnico, new TecnicoDTO());
        });

        assertEquals("Técnico no encontrado con el ID: " + idTecnico, exception.getMessage());
    }
}
