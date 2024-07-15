package com.utndds;

import org.junit.Test;

import com.utndds.heladerasApi.models.Validadores.ValidadorContraseñas.ValidadorContraseña;

import static org.junit.Assert.*;

public class ValidadorContraseñaTest {
    @Test
    public void testContraseñaSegura() {
        String contraseña = "Segura123!";
        assertTrue(new ValidadorContraseña().validarContraseña(contraseña));
    }

    @Test
    public void testContraseñaLongitudInsuficiente() {
        String contraseña = "hola1!";
        assertFalse(new ValidadorContraseña().validarContraseña(contraseña));
    }

    @Test
    public void testContraseñaSinCaracterEspecial() {
        String contraseña = "SinCaracterEspecial123";
        assertFalse(new ValidadorContraseña().validarContraseña(contraseña));
    }

    @Test
    public void testContraseñaComún() {
        String contraseña = "contraseña123";
        assertFalse(new ValidadorContraseña().validarContraseña(contraseña));
    }

    @Test
    public void testContraseñaVacia() {
        String contraseña = "";
        assertFalse(new ValidadorContraseña().validarContraseña(contraseña));
    }
}