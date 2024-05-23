package com.utndds.heladerasApi.models.Persona.Colaboradores;

import com.utndds.heladerasApi.models.Colaboraciones.Colaboracion;
import com.utndds.heladerasApi.models.Persona.Contacto.Contacto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PersonaHumana extends Colaborador {
    String nombre;
    String apellido;
    Date fechaDeNacimiento;
    List<Colaboracion> posiblesColaboraciones = new ArrayList<>();

    public PersonaHumana(List<Contacto> mediosDeContacto, String direccion, List<Colaboracion> colaboraciones,
            String nombre, String apellido, Date fechaDeNacimiento) {
        super(mediosDeContacto, direccion, colaboraciones);
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaDeNacimiento = fechaDeNacimiento;
    }
}
