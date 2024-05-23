package com.utndds.heladerasApi.models;

import java.util.ArrayList;
import java.util.List;

import com.utndds.heladerasApi.models.Colaboradores.Contacto;

public class Tecnico {
    String nombre;
    String apellido;
    String cuil;
    Documento documento;
    List<Contacto> mediosDeContacto = new ArrayList<>();
    String areaCobertura;

    public Tecnico(String nombre, String apellido, String cuil, Documento documento, List<Contacto> mediosDeContacto,
            String areaCobertura) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cuil = cuil;
        this.documento = documento;
        this.mediosDeContacto = mediosDeContacto;
        this.areaCobertura = areaCobertura;
    }

    public void alta() {
        System.out.println("Se realizo alta del tecnico: " + this.nombre);
    }

    public void baja() {
        System.out.println("Se realizo alta del tecnico: " + this.nombre);
    }

    public void modificar() {
        System.out.println("Se realizo la modificacion del tecnico: " + this.nombre);
    }

}
