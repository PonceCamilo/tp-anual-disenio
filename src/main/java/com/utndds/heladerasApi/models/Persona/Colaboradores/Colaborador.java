package com.utndds.heladerasApi.models.Persona.Colaboradores;

import java.util.ArrayList;
import java.util.List;

import com.utndds.heladerasApi.models.Colaboraciones.Colaboracion;
import com.utndds.heladerasApi.models.Persona.Contacto.Contacto;

public class Colaborador {
    List<Contacto> mediosDeContacto = new ArrayList<>();
    String direccion;
    List<Colaboracion> colaboraciones = new ArrayList<>();

    public Colaborador(List<Contacto> mediosDeContacto, String direccion, List<Colaboracion> colaboraciones) {
        this.direccion = direccion;
        this.mediosDeContacto = mediosDeContacto;
        this.colaboraciones = colaboraciones;
    }

    public void alta() {
        System.out.println("se dio de alta el colaborador");
    }

    public void baja() {
        System.out.println("se dio de baja el colaborador");
    }

    public void modificar() {
        System.out.println("se modifico el colaborador");
    }

    public void realizarColaboracion(Colaboracion colaboracion) {
        colaboracion.realizar();
        colaboraciones.add(colaboracion);
    }

    public void notificar() {
        System.out.println("se notifico al colaborador");
    }

    public double puntosActuales() {
        double puntos = 0;
        for (Colaboracion colaboracion : colaboraciones) {
            puntos += colaboracion.puntosGanados();
        }

        return puntos;
    }

    public void hola() {
        System.out.println(this.colaboraciones);
    }

}
