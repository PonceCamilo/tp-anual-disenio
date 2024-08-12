package com.utndds.heladerasApi.services.Validadores.ValidadorContraseñas.Condiciones;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.stereotype.Service;

@Service
public class PeoresContraseñas extends Condicion {
    String RUTA_PEORES_CONTRASEÑAS = "/peoresContraseñas.txt";

    @Override
    public boolean verificarCondicion(String contraseña) {
        try (InputStream inputStream = PeoresContraseñas.class.getResourceAsStream(RUTA_PEORES_CONTRASEÑAS);
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().equalsIgnoreCase(contraseña)) {
                    return false; // Retorna false si la contraseña está en la lista de peores contraseñas
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true; // Retorna true si la contraseña no está en la lista de peores contraseñas
    }

    @Override
    public void mostrarError() {
        System.out.println("la contraseña no tiene algun caracter especial");
    }
}
