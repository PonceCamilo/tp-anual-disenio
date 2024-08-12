package com.utndds.heladerasApi.models.Colaboraciones;

import java.time.LocalDate;

import com.utndds.heladerasApi.models.Rol.Colaborador;

public abstract class Colaboracion {
    LocalDate fecha;
    Colaborador colaborador;

    public Colaboracion(Colaborador colaborador) {
        this.fecha = LocalDate.now();
        this.colaborador = colaborador;
        this.procesar();
    }

    protected void procesar() {
        colaborador.agregarColaboracion(this);
        this.notificarColaborador("La colaboracion ha sido recibida correctamente.");
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

}
