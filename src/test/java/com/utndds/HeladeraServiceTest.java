package com.utndds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.repositories.HeladeraRepository;
import com.utndds.heladerasApi.services.HeladeraService;

import java.time.LocalDate;

public class HeladeraServiceTest {

    @InjectMocks
    private HeladeraService heladeraService;

    @Mock
    private HeladeraRepository heladeraRepository;

    private Heladera heladera;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        // Inicialización de una heladera de prueba
        heladera = new Heladera();
        heladera.setCapacidad(100);
        heladera.setFechaInicioFuncionamiento(LocalDate.now());
        heladera.setFuncionando(true);
    }

    @Test
    public void testCrearHeladera() {
        // Arrange
        when(heladeraRepository.save(any(Heladera.class))).thenReturn(heladera);

        // Act
        Heladera result = heladeraService.crearHeladera(heladera);

        // Assert
        verify(heladeraRepository).save(heladera);
        assertEquals(heladera, result);
    }

    @Test
    public void testEliminarHeladera() {
        // Arrange
        Long heladeraId = 1L;

        // Act
        heladeraService.eliminarHeladera(heladeraId);

        // Assert
        verify(heladeraRepository).deleteById(heladeraId);
    }

    @Test
    public void testActualizarHeladera() {
        // Arrange
        when(heladeraRepository.save(any(Heladera.class))).thenReturn(heladera);

        // Act
        Heladera result = heladeraService.actualizarHeladera(heladera);

        // Assert
        verify(heladeraRepository).save(heladera);
        assertEquals(heladera, result);
    }
}