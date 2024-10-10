package com.utndds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Heladera.Punto;
import com.utndds.heladerasApi.models.Heladera.Incidentes.VisitaTecnico;
import com.utndds.heladerasApi.models.Heladera.Incidentes.Incidente.FallaTecnica;
import com.utndds.heladerasApi.models.Heladera.Incidentes.Incidente.Incidente;
import com.utndds.heladerasApi.models.Persona.Persona;
import com.utndds.heladerasApi.models.Rol.Tecnico.Tecnico;
import com.utndds.heladerasApi.repositories.TecnicoRepository;
import com.utndds.heladerasApi.repositories.VisitaTecnicoRepository;
import com.utndds.heladerasApi.repositories.Incidentes.IncidenteRepository;
import com.utndds.heladerasApi.services.IncidenteService;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
    private Heladera heladera;

    @Mock
    private Tecnico tecnico;

    @Mock
    private Persona persona;

    @Mock
    private Incidente incidente;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        System.setOut(new PrintStream(outContent)); // Redirigir la salida estándar
    }

    @Test
    public void testReportarIncidente() {
        // Arrange
        FallaTecnica incidente = new FallaTecnica();
        incidente.setHeladera(heladera);

        // Act
        incidenteService.reportarIncidente(incidente);

        // Assert
        verify(incidenteRepository).save(incidente);
        verify(tecnicoRepository).findTecnicoCercano(anyDouble(), anyDouble()); // Modificamos para verificar la lógica
                                                                                // de notificación del técnico
    }

    @Test
    public void testNotificarTecnicoCercano_TecnicoEncontrado() {
        // Arrange
        when(incidente.getHeladera()).thenReturn(heladera);
        when(heladera.getPunto()).thenReturn(new Punto(12.34, 56.78, "Punto1", "Dirección de prueba"));
        when(tecnicoRepository.findTecnicoCercano(12.34, 56.78)).thenReturn(tecnico);
        when(tecnico.getPersona()).thenReturn(persona);

        // Act
        incidenteService.notificarTecnicoCercano(incidente);

        // Assert
        String mensajeEsperado = "NECESITAMOS TUS SERVICIOS EN LA DIRECCION: Dirección de prueba";
        verify(persona).notificar(mensajeEsperado);
        verify(visitaTecnicoRepository).save(any(VisitaTecnico.class)); // Verificamos que la visita se haya registrado
    }

    @Test
    public void testNotificarTecnicoCercano_TecnicoNoEncontrado() {
        // Arrange
        when(incidente.getHeladera()).thenReturn(heladera);
        when(heladera.getPunto()).thenReturn(new Punto(12.34, 56.78, "Punto1", "Dirección de prueba"));
        when(tecnicoRepository.findTecnicoCercano(12.34, 56.78)).thenReturn(null);

        // Act
        incidenteService.notificarTecnicoCercano(incidente);

        // Assert
        String mensajeEsperado = "No se encontró un técnico cercano para la heladera en la dirección: Dirección de prueba";
        assertEquals(mensajeEsperado, outContent.toString().trim());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut); // Restaurar la salida estándar original
    }
}