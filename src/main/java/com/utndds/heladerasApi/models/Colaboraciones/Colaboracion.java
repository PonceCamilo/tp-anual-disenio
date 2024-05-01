package com.utndds.heladerasApi.models.Colaboraciones;

import com.utndds.heladerasApi.models.Colaboradores.Colaborador;

import java.util.Date;

public class Colaboracion {
    Date fecha;
    Colaborador colaborador;

    public Colaboracion(Date fecha, Colaborador colaborador) {
        this.fecha = fecha;
        this.colaborador = colaborador;
    }
}
