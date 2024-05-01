package com.utndds.heladerasApi.models.Colaboraciones;

import com.utndds.heladerasApi.models.Colaboradores.Colaborador;

import java.util.Date;

public class DonacionDinero extends Colaboracion{
    double monto;

    public DonacionDinero(Date fecha, Colaborador colaborador, double monto) {
        super(fecha, colaborador);
        this.monto = monto;
    }
}
