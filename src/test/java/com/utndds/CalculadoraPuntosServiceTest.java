package com.utndds;

import com.utndds.heladerasApi.models.Colaboraciones.*;
import com.utndds.heladerasApi.models.Colaboraciones.DonacionViandas.DonacionVianda;
import com.utndds.heladerasApi.models.Colaboraciones.DonacionViandas.Vianda;
import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Persona.PersonaHumana;
import com.utndds.heladerasApi.models.Rol.Colaborador;
import com.utndds.heladerasApi.models.Rol.PersonaVulnerable;
import com.utndds.heladerasApi.models.Tarjetas.TarjetaPersVuln;
import com.utndds.heladerasApi.repositories.ColaboracionesRepositories.ColaboracionRepository;
import com.utndds.heladerasApi.repositories.ColaboradorRepository;
import com.utndds.heladerasApi.services.Canjes.CalculadoraPuntosService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CalculadoraPuntosServiceTest {

    @InjectMocks
    private CalculadoraPuntosService calculadoraPuntosService;

    @Mock
    private ColaboracionRepository colaboracionRepository;

    @Mock
    private ColaboradorRepository colaboradorRepository;

    @SuppressWarnings("deprecation")
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCalcularPuntos_WithMultipleContributions() {
        // Arrange
        PersonaHumana persona = new PersonaHumana();
        persona.setNombre("John Doe");

        Colaborador colaborador = new Colaborador();
        colaborador.setPersona(persona);
        colaborador.setPuntosGastados(9.5);

        List<Colaboracion> colaboraciones = new ArrayList<>();
        colaboraciones.add(new DonacionDinero(colaborador, 100.0, 1));
        colaboraciones.add(new DonacionVianda(colaborador, new Vianda(), new Heladera()));
        colaboraciones.add(new RegistroPersonaVulnerable(colaborador, new PersonaVulnerable(), new TarjetaPersVuln()));
        colaboraciones.add(new RegistroPersonaVulnerable(colaborador, new PersonaVulnerable(), new TarjetaPersVuln()));
        colaboraciones.add(new RegistroPersonaVulnerable(colaborador, new PersonaVulnerable(), new TarjetaPersVuln()));
        colaboraciones.add(new RegistroPersonaVulnerable(colaborador, new PersonaVulnerable(), new TarjetaPersVuln()));

        String mockUUID = "test-uuid";

        // Mock the findByUUID method to return the colaborador for the specific UUID
        when(colaboradorRepository.findByUUID(mockUUID)).thenReturn(Optional.of(colaborador));
        when(colaboracionRepository.findByColaborador(colaborador)).thenReturn(colaboraciones);

        // Act
        double puntos = calculadoraPuntosService.calcularPuntos(mockUUID);

        // Assert
        assertEquals(50, puntos);
    }
}