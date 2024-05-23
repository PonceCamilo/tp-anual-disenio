package com.utndds.heladerasApi.models.Colaboraciones;

import java.time.LocalDate;

import com.utndds.heladerasApi.models.Persona.Colaboradores.Colaborador;

public abstract class Colaboracion {
    LocalDate fecha;
    Colaborador colaborador;

    public Colaboracion(LocalDate fecha, Colaborador colaborador) {
        this.fecha = fecha;
        this.colaborador = colaborador;
    }

    public abstract void realizar();

    protected void notificarColaborador() {
        colaborador.notificar();
    };

    public double puntosGanados() {
        return 0;
    };

    protected double obtenerCoeficiente() {
        return 0;
    };

}
