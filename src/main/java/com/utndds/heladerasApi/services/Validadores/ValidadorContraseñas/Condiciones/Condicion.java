package com.utndds.heladerasApi.services.Validadores.ValidadorContraseñas.Condiciones;

public abstract class Condicion {

    public abstract boolean verificarCondicion(String contraseña);

    public abstract void mostrarError();
}