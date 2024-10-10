package com.utndds;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.utndds.heladerasApi.services.Validadores.ValidadorContraseñas.ValidadorContraseña;
import com.utndds.heladerasApi.services.Validadores.ValidadorContraseñas.Condiciones.CaracteresEspeciales;
import com.utndds.heladerasApi.services.Validadores.ValidadorContraseñas.Condiciones.LongitudMinima;
import com.utndds.heladerasApi.services.Validadores.ValidadorContraseñas.Condiciones.PeoresContraseñas;

public class ValidadorContraseñaTest {

    @InjectMocks
    private ValidadorContraseña validador;

    @Mock
    private LongitudMinima longitudMinima;

    @Mock
    private PeoresContraseñas peoresContraseñas;

    @Mock
    private CaracteresEspeciales caracteresEspeciales;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        validador.agregarCondicion(longitudMinima);
        validador.agregarCondicion(peoresContraseñas);
        validador.agregarCondicion(caracteresEspeciales);
    }

    @Test
    public void testValidarContraseña_Segura() {
        // Arrange
        String contraseñaSegura = "Segura123!";
        when(longitudMinima.verificarCondicion(contraseñaSegura)).thenReturn(true);
        when(peoresContraseñas.verificarCondicion(contraseñaSegura)).thenReturn(true);
        when(caracteresEspeciales.verificarCondicion(contraseñaSegura)).thenReturn(true);

        // Act
        boolean resultado = validador.validarContraseña(contraseñaSegura);

        // Assert
        assertTrue(resultado);
    }

    @Test
    public void testValidarContraseña_NoSegura() {
        // Arrange
        String contraseñaInsegura = "Segura123";
        when(longitudMinima.verificarCondicion(contraseñaInsegura)).thenReturn(true);
        when(peoresContraseñas.verificarCondicion(contraseñaInsegura)).thenReturn(false);
        when(caracteresEspeciales.verificarCondicion(contraseñaInsegura)).thenReturn(false);

        // Act
        boolean resultado = validador.validarContraseña(contraseñaInsegura);

        // Assert
        assertFalse(resultado);
    }

    // Agrega más pruebas para otras condiciones según sea necesario
}