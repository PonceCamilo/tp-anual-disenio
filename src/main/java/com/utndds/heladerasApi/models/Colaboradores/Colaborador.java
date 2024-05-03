package com.utndds.heladerasApi.models.Colaboradores;

import java.util.ArrayList;
import java.util.List; // Add this import statement
import com.utndds.heladerasApi.models.Colaboradores.Contacto; // Add this import statement

public class Colaborador {

    List<Contacto> mediosDeContacto = new ArrayList<Contacto>();
    String direccion;

    public Colaborador(String direccion) {
        this.direccion = direccion;
    }
}
