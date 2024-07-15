package com.utndds.heladerasApi.models.Validadores.ValidadorContraseñas.Condiciones;

public class LongitudMinima extends Condicion {
    int LONGITUD_MINIMA_CONTRASEÑA = 8;

    @Override
    public boolean verificarCondicion(String contraseña) {
        if (contraseña.length() < LONGITUD_MINIMA_CONTRASEÑA) {
            return true;
        }
        return false;
    }

    @Override
    public void mostrarError() {
        System.out.println("la contraseña no cumple con la longitud minima");
    }

}
