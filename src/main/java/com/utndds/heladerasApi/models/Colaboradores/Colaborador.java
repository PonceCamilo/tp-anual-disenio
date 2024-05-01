package com.utndds.heladerasApi.models.Colaboradores;

import java.util.ArrayList;

public class Colaborador {

    List<Contacto> mediosDeContacto = new ArrayList<Contacto>();
    String direccion;

    public Colaborador(String direccion) {
        this.direccion = direccion;
    }
}
