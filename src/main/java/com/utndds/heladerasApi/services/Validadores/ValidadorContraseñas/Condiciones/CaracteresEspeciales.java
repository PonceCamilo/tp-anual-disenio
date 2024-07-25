package com.utndds.heladerasApi.services.Validadores.ValidadorContraseñas.Condiciones;

public class CaracteresEspeciales extends Condicion {
    String CARACTERES_ESPECIALES = "!#$%&'()*+,-./:;<=>?@[]^_`{|}~";

    @Override
    public boolean verificarCondicion(String contraseña) {
        for (char c : contraseña.toCharArray()) {
            if (CARACTERES_ESPECIALES.contains(String.valueOf(c))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void mostrarError() {
        System.out.println("la contraseña no tiene algun caracter especial");
    }

}
