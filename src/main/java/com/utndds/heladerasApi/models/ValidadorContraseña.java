package com.utndds.heladerasApi.models;
import java.util.Arrays;

public class ValidadorContraseña {
    private static final String[] PEORES_CONTRASEÑAS = new String[10000];

    private static final int LONGITUD_MINIMA_CONTRASEÑA = 8;
    private static final String CARACTERES_ESPECIALES = "!@#$%^&*()_+{}[]";

    public static boolean esDebil(String contraseña) {
        if (contraseña.length() < LONGITUD_MINIMA_CONTRASEÑA) {
            return true;
        }

        if (!contieneCaracterEspecial(contraseña)) {
            return true;
        }

        if (esContraseñaComún(contraseña)) {
            return true;
        }

        return false;
    }

    private static boolean contieneCaracterEspecial(String contraseña) {
        for (char c : contraseña.toCharArray()) {
            if (CARACTERES_ESPECIALES.contains(String.valueOf(c))) {
                return true;
            }
        }
        return false;
    }

    private static boolean esContraseñaComún(String contraseña) {
        return Arrays.asList(PEORES_CONTRASEÑAS).contains(contraseña.toLowerCase());
    }

    public static void main(String[] args) {
        String contraseña = "contraseña123";
        if (esDebil(contraseña)) {
            System.out.println("La contraseña es débil. Por favor, elija una contraseña más segura.");
        } else {
            System.out.println("La contraseña es segura.");
        }
    }
}