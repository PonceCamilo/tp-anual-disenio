package com.utndds;

import com.utndds.heladerasApi.models.Colaboraciones.Ofertas.Canje;
import com.utndds.heladerasApi.models.Colaboraciones.Ofertas.Oferta;
import com.utndds.heladerasApi.models.Rol.Colaborador;
import com.utndds.heladerasApi.repositories.CanjeRepository;
import com.utndds.heladerasApi.repositories.ColaboradorRepository;
import com.utndds.heladerasApi.repositories.ColaboracionesRepositories.OfertaRepository;
import com.utndds.heladerasApi.services.Canjes.CalculadoraPuntosService;
import com.utndds.heladerasApi.services.Canjes.CanjesService;

import jakarta.persistence.EntityNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CanjesServiceTest {

    @InjectMocks
    private CanjesService canjesService;

    @Mock
    private ColaboradorRepository colaboradorRepository;

    @Mock
    private OfertaRepository ofertaRepository;

    @Mock
    private CalculadoraPuntosService calculadoraPuntosService;

    @Mock
    private CanjeRepository canjeRepository;

    @Mock
    private Colaborador colaborador;

    @Mock
    private Oferta oferta;

    @BeforeEach
    public void setUp() {
        // Configura el comportamiento de los mocks aquí, si es necesario
    }

    @Test
    public void testCanjearOferta_Exitoso() {
        Long colaboradorId = 1L;
        Long ofertaId = 1L;
        double puntosNecesarios = 10.0;
        double puntosDisponibles = 15.0;

        // Configurar los mocks
        when(colaboradorRepository.findById(colaboradorId)).thenReturn(Optional.of(colaborador));
        when(ofertaRepository.findById(ofertaId)).thenReturn(Optional.of(oferta));
        when(oferta.getCantidadPuntosNec()).thenReturn(puntosNecesarios);
        when(calculadoraPuntosService.calcularPuntos(colaborador)).thenReturn(puntosDisponibles);

        // Ejecutar el método
        boolean resultado = canjesService.canjearOferta(colaboradorId, ofertaId);

        // Verificaciones
        assertTrue(resultado);
        verify(canjeRepository, times(1)).save(any(Canje.class)); // Verifica que se guardó el canje
        verify(colaboradorRepository, times(1)).save(colaborador); // Verifica que se guardó el colaborador
    }

    @Test
    public void testCanjearOferta_Fallido_SinPuntosSuficientes() {
        Long colaboradorId = 1L;
        Long ofertaId = 1L;
        double puntosNecesarios = 10.0;
        double puntosDisponibles = 5.0;

        // Configurar los mocks
        when(colaboradorRepository.findById(colaboradorId)).thenReturn(Optional.of(colaborador));
        when(ofertaRepository.findById(ofertaId)).thenReturn(Optional.of(oferta));
        when(oferta.getCantidadPuntosNec()).thenReturn(puntosNecesarios);
        when(calculadoraPuntosService.calcularPuntos(colaborador)).thenReturn(puntosDisponibles);

        // Ejecutar el método
        boolean resultado = canjesService.canjearOferta(colaboradorId, ofertaId);

        // Verificaciones
        assertFalse(resultado);
        verify(canjeRepository, never()).save(any(Canje.class)); // Verifica que no se guardó el canje
        verify(colaboradorRepository, never()).save(colaborador); // Verifica que no se guardó el colaborador
    }

    @Test
    public void testCanjearOferta_ColaboradorNoEncontrado() {
        Long colaboradorId = 1L;
        Long ofertaId = 1L;

        // Configurar el mock
        when(colaboradorRepository.findById(colaboradorId)).thenReturn(Optional.empty());

        // Verificar que se lance la excepción
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            canjesService.canjearOferta(colaboradorId, ofertaId);
        });
        assertEquals("Colaborador no encontrado con id " + colaboradorId, exception.getMessage());
    }

    @Test
    public void testCanjearOferta_OfertaNoEncontrada() {
        Long colaboradorId = 1L;
        Long ofertaId = 1L;

        // Configurar el mock para el colaborador
        when(colaboradorRepository.findById(colaboradorId)).thenReturn(Optional.of(colaborador));
        when(ofertaRepository.findById(ofertaId)).thenReturn(Optional.empty());

        // Verificar que se lance la excepción
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            canjesService.canjearOferta(colaboradorId, ofertaId);
        });
        assertEquals("Oferta no encontrada con id " + ofertaId, exception.getMessage());
    }
}