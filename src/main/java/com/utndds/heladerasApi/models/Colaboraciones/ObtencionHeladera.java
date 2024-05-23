package com.utndds.heladerasApi.models.Colaboraciones;

import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Persona.Colaboradores.Colaborador;

import java.time.LocalDate;

public class ObtencionHeladera extends Colaboracion {

    Heladera heladera;

    public ObtencionHeladera(LocalDate fecha, Colaborador colaborador, Heladera heladera) {
        super(fecha, colaborador);
        this.heladera = heladera;
    }

    @Override
    public void realizar() {
        System.out.println("SE REALIZADO LA OBTENCION DE LA HELADERA");
    }

    @Override
    public double puntosGanados() {
        System.out.println("CORREGIR PUNTOS GANADOS");
        return 25;
    }

    @Override
    protected double obtenerCoeficiente() {
        System.out.println("COMPLETAR COMO OBTENER COEFICIENTE");
        return 23;
    }

}
