package com.utndds.heladerasApi.models.Persona;

import java.util.ArrayList;
import java.util.List;

import com.utndds.heladerasApi.models.Persona.Contacto.Contacto;

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

}
