package com.utndds;

import org.junit.Test;

import com.utndds.heladerasApi.models.Validadores.ValidadorContraseña;

import static org.junit.Assert.*;

public class ValidadorContraseñaTest {
    @Test
    public void testContraseñaSegura() {
        String contraseña = "Segura123!";
        assertFalse(ValidadorContraseña.esDebil(contraseña));
    }

    @Test
    public void testContraseñaLongitudInsuficiente() {
        String contraseña = "hola1!";
        assertTrue(ValidadorContraseña.esDebil(contraseña));
    }

    @Test
    public void testContraseñaSinCaracterEspecial() {
        String contraseña = "SinCaracterEspecial123";
        assertTrue(ValidadorContraseña.esDebil(contraseña));
    }

    @Test
    public void testContraseñaComún() {
        String contraseña = "contraseña123";
        assertTrue(ValidadorContraseña.esDebil(contraseña));
    }

    @Test
    public void testContraseñaVacia() {
        String contraseña = "";
        assertTrue(ValidadorContraseña.esDebil(contraseña));
    }
}