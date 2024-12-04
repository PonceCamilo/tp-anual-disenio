package com.utndds.heladerasApi.services.Validadores.ValidadorContraseñas.Condiciones;

import org.springframework.stereotype.Service;

@Service
public class LongitudMinima extends Condicion {
    int LONGITUD_MINIMA_CONTRASEÑA = 8;

    @Override
    public boolean verificarCondicion(String contraseña) {
        if (contraseña.length() < LONGITUD_MINIMA_CONTRASEÑA) {
            return false;
        }
        return true;
    }

    @Override
    public void mostrarError() {
        System.out.println("la contraseña no cumple con la longitud minima");
    }

}
