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

import com.utndds.heladerasApi.DTOs.FallaTecnicaDTO;
import com.utndds.heladerasApi.DTOs.VisitaTecnicoDTO;
import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Heladera.Punto;
import com.utndds.heladerasApi.models.Heladera.Incidentes.VisitaTecnico;
import com.utndds.heladerasApi.models.Heladera.Incidentes.Incidente.FallaTecnica;
import com.utndds.heladerasApi.models.Heladera.Incidentes.Incidente.Incidente;
import com.utndds.heladerasApi.models.Persona.PersonaHumana;
import com.utndds.heladerasApi.models.Rol.Colaborador;
import com.utndds.heladerasApi.models.Rol.Tecnico.Tecnico;
import com.utndds.heladerasApi.repositories.ColaboradorRepository;
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
    private ColaboradorRepository colaboradorRepository;
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
    public void testReportarFallaTecnica() {
        // Datos de entrada
        String colaboradorUUID = "uuid-colaborador-1";
        FallaTecnicaDTO fallaTecnicaDTO = new FallaTecnicaDTO();
        fallaTecnicaDTO.setHeladeraId(1L);
        fallaTecnicaDTO.setDescripcion("Falla en el compresor");
        fallaTecnicaDTO.setFoto("foto.jpg");

        // Configuración de mocks
        Colaborador colaborador = mock(Colaborador.class);
        when(colaboradorRepository.findByUUID(colaboradorUUID)).thenReturn(Optional.of(colaborador));

        // Crear un mock de Heladera y Punto
        Heladera heladera = mock(Heladera.class);
        Punto punto = mock(Punto.class);
        when(punto.getLatitud()).thenReturn(1.0);
        when(punto.getLongitud()).thenReturn(1.0);
        when(heladera.getPunto()).thenReturn(punto);
        when(heladeraRepository.findById(fallaTecnicaDTO.getHeladeraId())).thenReturn(Optional.of(heladera));

        // Mock para el técnico más cercano
        Tecnico tecnicoCercano = mock(Tecnico.class);
        when(tecnicoRepository.findTecnicoCercano(anyDouble(), anyDouble())).thenReturn(tecnicoCercano);
        when(tecnicoCercano.getPersona()).thenReturn(personaHumana);

        // Ejecutar el método
        incidenteService.reportarFallaTecnica(colaboradorUUID, fallaTecnicaDTO);

        // Verificaciones
        verify(incidenteRepository, times(1)).save(any(FallaTecnica.class)); // Verificar que se guarda la falla técnica
        verify(tecnicoCercano.getPersona(), times(1)).notificar(anyString()); // Verificar que se llamó al notificar
        verify(visitaTecnicoRepository, times(1)).save(any(VisitaTecnico.class)); // Verificar que se guarda la visita
                                                                                  // del técnico
    }

    @Test
    public void testRegistrarVisita_TecnicoNoEncontrado() {
        // No es necesario el stub para visitaDTO.getIncidenteId()
        when(tecnicoRepository.findByUUID("uuid-tecnico-1")).thenReturn(Optional.empty());

        // Verificación de excepción
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            incidenteService.registrarVisita("uuid-tecnico-1", visitaDTO);
        });
        assertEquals("Técnico no encontrado con uuid uuid-tecnico-1", exception.getMessage());
    }

    @Test
    public void testRegistrarVisita_IncidenteNoEncontrado() {
        // Datos de entrada
        when(visitaDTO.getIncidenteId()).thenReturn(1L);
        when(tecnicoRepository.findByUUID("uuid-tecnico-1")).thenReturn(Optional.of(tecnico));
        when(incidenteRepository.findById(1L)).thenReturn(Optional.empty());

        // Verificación de excepción
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            incidenteService.registrarVisita("uuid-tecnico-1", visitaDTO);
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