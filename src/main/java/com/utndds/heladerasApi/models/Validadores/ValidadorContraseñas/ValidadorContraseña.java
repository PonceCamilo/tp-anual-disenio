package com.utndds.heladerasApi.models.Validadores.ValidadorContraseñas;

import java.util.ArrayList;
import java.util.List;

import com.utndds.heladerasApi.models.Validadores.ValidadorContraseñas.Condiciones.CaracteresEspeciales;
import com.utndds.heladerasApi.models.Validadores.ValidadorContraseñas.Condiciones.Condicion;
import com.utndds.heladerasApi.models.Validadores.ValidadorContraseñas.Condiciones.LongitudMinima;
import com.utndds.heladerasApi.models.Validadores.ValidadorContraseñas.Condiciones.PeoresContraseñas;

public class ValidadorContraseña {
    private List<Condicion> condiciones = new ArrayList<>();

    public ValidadorContraseña() {
        condiciones.add(new LongitudMinima());
        condiciones.add(new PeoresContraseñas());
        condiciones.add(new CaracteresEspeciales());
    }

    public boolean validarContraseña(String contraseña) {
        for (Condicion condicion : condiciones) {
            if (condicion.verificarCondicion(contraseña) == true) {
                continue;
            } else {
                condicion.mostrarError();
                return false;
            }
        }
        System.out.println("La contraseña es segura.");
        return true;
    }

    public void agregarCondicion(Condicion condicion) {
        condiciones.add(condicion);
    }

    public static void main(String[] args) {
        String contraseña = "Segura123!";
        ValidadorContraseña validador = new ValidadorContraseña();
        validador.validarContraseña(contraseña);
    }
}