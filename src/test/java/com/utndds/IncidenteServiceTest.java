package com.utndds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.utndds.heladerasApi.DTOs.VisitaTecnicoDTO;
import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Heladera.Punto;
import com.utndds.heladerasApi.models.Heladera.Incidentes.VisitaTecnico;
import com.utndds.heladerasApi.models.Heladera.Incidentes.Incidente.Incidente;
import com.utndds.heladerasApi.models.Persona.PersonaHumana;
import com.utndds.heladerasApi.models.Rol.Tecnico.Tecnico;
import com.utndds.heladerasApi.repositories.HeladeraRepository;
import com.utndds.heladerasApi.repositories.TecnicoRepository;
import com.utndds.heladerasApi.repositories.VisitaTecnicoRepository;
import com.utndds.heladerasApi.repositories.Incidentes.IncidenteRepository;
import com.utndds.heladerasApi.services.IncidenteService;

import jakarta.persistence.EntityNotFoundException;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class IncidenteServiceTest {

    @InjectMocks
    private IncidenteService incidenteService;

    @Mock
    private TecnicoRepository tecnicoRepository;

    @Mock
    private IncidenteRepository incidenteRepository;

    @Mock
    private VisitaTecnicoRepository visitaTecnicoRepository;

    @Mock
    private HeladeraRepository heladeraRepository;

    @Mock
    private Heladera heladera;

    @Mock
    private Tecnico tecnico;

    @Mock
    private Incidente incidente;

    @Mock
    private VisitaTecnicoDTO visitaDTO;

    @Mock
    private PersonaHumana personaHumana; // Agregar este mock

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testReportarIncidente() {
        // Datos de entrada
        when(incidente.getHeladera()).thenReturn(heladera);
        when(heladera.getPunto()).thenReturn(new Punto(1.0, 1.0, null, null)); // Suponiendo que tienes una clase Punto

        // Configuración de mocks
        when(tecnicoRepository.findTecnicoCercano(anyDouble(), anyDouble())).thenReturn(tecnico);
        when(tecnico.getPersona()).thenReturn(personaHumana); // Cambiar aquí

        // Ejecutar el método
        incidenteService.reportarIncidente(incidente);

        // Verificaciones
        verify(incidenteRepository, times(1)).save(incidente);
        verify(personaHumana, times(1)).notificar(anyString()); // Cambiar aquí
        verify(visitaTecnicoRepository, times(1)).save(any(VisitaTecnico.class));
    }

    @Test
    public void testRegistrarVisita_TecnicoNoEncontrado() {
        // No es necesario el stub para visitaDTO.getIncidenteId()
        when(tecnicoRepository.findById(1L)).thenReturn(Optional.empty());

        // Verificación de excepción
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            incidenteService.registrarVisita(1L, visitaDTO);
        });
        assertEquals("Técnico no encontrado con id 1", exception.getMessage());
    }

    @Test
    public void testRegistrarVisita_IncidenteNoEncontrado() {
        // Datos de entrada
        when(visitaDTO.getIncidenteId()).thenReturn(1L);
        when(tecnicoRepository.findById(1L)).thenReturn(Optional.of(tecnico));
        when(incidenteRepository.findById(1L)).thenReturn(Optional.empty());

        // Verificación de excepción
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            incidenteService.registrarVisita(1L, visitaDTO);
        });
        assertEquals("Incidente no encontrado con id 1", exception.getMessage());
    }

    @Test
    public void testGenerarAlerta_HeladeraNoEncontrada() {
        Long heladeraId = 1L;
        String tipoAlerta = "Alerta de prueba";

        when(heladeraRepository.findById(heladeraId)).thenReturn(Optional.empty());

        // Verificación de excepción
        Exception exception = assertThrows(RuntimeException.class, () -> {
            incidenteService.generarAlerta(heladeraId, tipoAlerta);
        });
        assertEquals("Heladera no encontrada con ID: 1", exception.getMessage());
    }
}