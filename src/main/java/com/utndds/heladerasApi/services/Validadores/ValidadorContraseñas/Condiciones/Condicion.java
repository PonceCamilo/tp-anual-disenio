package com.utndds.heladerasApi.services.Validadores.ValidadorContraseñas.Condiciones;

import org.springframework.stereotype.Service;

@Service
public abstract class Condicion {

    public abstract boolean verificarCondicion(String contraseña);

    public abstract void mostrarError();
}