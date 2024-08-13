package com.utndds.heladerasApi.models.Rol;

import java.util.ArrayList;
import java.util.List;

import com.utndds.heladerasApi.models.Colaboraciones.Colaboracion;
import com.utndds.heladerasApi.models.Heladera.Suscripciones.Suscripcion;
import com.utndds.heladerasApi.models.Persona.Persona;

public class Colaborador extends Rol {
    List<Colaboracion> colaboraciones = new ArrayList<>();
    List<Suscripcion> suscripciones = new ArrayList<>();

    public Colaborador(Persona persona, List<Colaboracion> colaboraciones) {
        super(persona);
        this.colaboraciones = colaboraciones;
    }

    @Override
    public Persona getPersona() {
        return (Persona) persona;
    }

    public double puntos() {
        double puntos = 0;
        for (Colaboracion colaboracion : colaboraciones) {
            puntos += colaboracion.puntosGanados();
        }

        return puntos;
    }

    public void agregarColaboracion(Colaboracion colaboracion) {
        colaboraciones.add(colaboracion);
    }

    public void agregarSuscripcion(Suscripcion suscripcion) {
        suscripciones.add(suscripcion);
    }

    public List<Colaboracion> getColaboraciones() {
        return colaboraciones;
    }

}
