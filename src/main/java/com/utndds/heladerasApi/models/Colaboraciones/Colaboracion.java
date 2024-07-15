package com.utndds.heladerasApi.models.Colaboraciones;

import java.time.LocalDate;

import com.utndds.heladerasApi.models.Rol.Colaborador;

public abstract class Colaboracion {
    LocalDate fecha;
    Colaborador colaborador;

    public Colaboracion(LocalDate fecha, Colaborador colaborador) {
        this.fecha = fecha;
        this.colaborador = colaborador;

        this.procesar();
        colaborador.agregarColaboracion(this);
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void procesar() {
        colaborador.agregarColaboracion(this);
    }

    protected void notificarColaborador(String mensaje) {
        colaborador.notificar(mensaje);
    };

    public double puntosGanados() {
        return 0;
    };

    protected double obtenerCoeficiente() {
        return 0;
    };

}
