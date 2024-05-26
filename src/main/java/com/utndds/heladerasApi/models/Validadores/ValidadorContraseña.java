package com.utndds.heladerasApi.models.Validadores;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ValidadorContraseña {

    private static final int LONGITUD_MINIMA_CONTRASEÑA = 8;
    private static final String CARACTERES_ESPECIALES = "!#$%&'()*+,-./:;<=>?@[]^_`{|}~";
    private static final String RUTA_PEORES_CONTRASEÑAS = "/peoresContraseñas.txt";

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
        try {
            InputStream inputStream = ValidadorContraseña.class.getResourceAsStream(RUTA_PEORES_CONTRASEÑAS);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().equalsIgnoreCase(contraseña)) {
                    return true;
                }
            }

            br.close();
            inputStream.close();
        } catch (Exception e) {
            System.err.println("Error al leer el archivo de contraseñas comunes: " + e.getMessage());
        }
        return false;
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