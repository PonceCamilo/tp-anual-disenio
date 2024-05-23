package com.utndds.heladerasApi.models.Colaboraciones;

import com.utndds.heladerasApi.models.Colaboradores.Colaborador;

import java.time.LocalDate;

public class Colaboracion {
    LocalDate fecha;
    Colaborador colaborador;

    public Colaboracion(LocalDate fecha, Colaborador colaborador) {
        this.fecha = fecha;
        this.colaborador = colaborador;
    }

    public void realizar() {
    }

    private void notificarColaborador() {
        colaborador.notificar();
    }
}
