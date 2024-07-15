package com.utndds.heladerasApi.models.Rol;

import java.util.ArrayList;
import java.util.List;

import com.utndds.heladerasApi.models.Colaboraciones.Colaboracion;
import com.utndds.heladerasApi.models.Persona.Persona;
import com.utndds.heladerasApi.models.Rol.Contacto.Contacto;

public class Colaborador extends Rol {
    String direccion;
    List<Colaboracion> colaboraciones = new ArrayList<>();

    public Colaborador(Persona persona, List<Contacto> mediosContacto, String direccion,
            List<Colaboracion> colaboraciones) {
        super(persona, mediosContacto);
        this.direccion = direccion;
        this.colaboraciones = colaboraciones;
    }

    @Override
    public Persona getPersona() {
        return (Persona) persona;
    }

    public void agregarColaboracion(Colaboracion colaboracion) {
        colaboraciones.add(colaboracion);
    }

    public double puntos() {
        double puntos = 0;
        for (Colaboracion colaboracion : colaboraciones) {
            puntos += colaboracion.puntosGanados();
        }

        return puntos;
    }

}
