package com.utndds.ABMtests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.utndds.heladerasApi.HeladerasApiApplication;
import com.utndds.heladerasApi.DTOs.HeladeraDTO;
import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.repositories.HeladeraRepository;
import com.utndds.heladerasApi.services.ABM.HeladeraService;

import jakarta.persistence.EntityNotFoundException;

import java.util.Optional;

@SpringBootTest(classes = HeladerasApiApplication.class)
public class HeladeraServiceTest {

    @Mock
    private HeladeraRepository heladeraRepository;

    @InjectMocks
    private HeladeraService heladeraService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCrearHeladera() {
        // Arrange
        HeladeraDTO heladeraDTO = new HeladeraDTO();
        heladeraDTO.setCapacidad(500);
        heladeraDTO.setTemperaturaMaxima(5.0);
        heladeraDTO.setTemperaturaMinima(-5.0);

        Heladera heladera = new Heladera();
        heladera.setCapacidad(500);
        heladera.setTempMax(5.0);
        heladera.setTempMin(-5.0);

        when(heladeraRepository.save(any(Heladera.class))).thenReturn(heladera);

        // Act
        heladeraService.crearHeladera(heladeraDTO);

        // Assert
        verify(heladeraRepository, times(1)).save(any(Heladera.class));
    }

    @Test
    public void testEliminarHeladera() {
        // Arrange
        Long id = 1L;

        // Act
        heladeraService.eliminarHeladera(id);

        // Assert
        verify(heladeraRepository, times(1)).deleteById(id);
    }

    @Test
    public void testActualizarHeladera_Exito() {
        // Arrange
        Long id = 1L;
        HeladeraDTO heladeraDTO = new HeladeraDTO();
        heladeraDTO.setCapacidad(600);
        heladeraDTO.setTemperaturaMaxima(7.0);
        heladeraDTO.setTemperaturaMinima(-3.0);

        Heladera heladeraExistente = new Heladera();
        heladeraExistente.setCapacidad(500);
        heladeraExistente.setTempMax(5.0);
        heladeraExistente.setTempMin(-5.0);

        when(heladeraRepository.findById(id)).thenReturn(Optional.of(heladeraExistente));

        // Act
        heladeraService.actualizarHeladera(id, heladeraDTO);

        // Assert
        assertEquals(600, heladeraExistente.getCapacidad());
        assertEquals(7, heladeraExistente.getTempMax());
        assertEquals(-3, heladeraExistente.getTempMin());
        verify(heladeraRepository, times(1)).save(heladeraExistente);
    }

    @Test
    public void testActualizarHeladera_NoEncontrada() {
        // Arrange
        Long id = 1L;
        HeladeraDTO heladeraDTO = new HeladeraDTO();

        when(heladeraRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            heladeraService.actualizarHeladera(id, heladeraDTO);
        });

        assertEquals("Heladera no encontrada con el ID: " + id, exception.getMessage());
        verify(heladeraRepository, times(0)).save(any(Heladera.class));
    }
}