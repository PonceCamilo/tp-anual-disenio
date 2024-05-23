package com.utndds.heladerasApi.models.Colaboraciones;

import com.utndds.heladerasApi.models.Colaboradores.Colaborador;

import java.time.LocalDate;

public class DonacionDinero extends Colaboracion {
    double monto;
    int frecuencia;

    public DonacionDinero(LocalDate fecha, Colaborador colaborador, double monto, int frecuencia) {
        super(fecha, colaborador);
        this.monto = monto;
        this.frecuencia = frecuencia;
    }

    @Override
    public void realizar() {
        System.out.println("COMPLETAR LA DONACION DINERO");
    }
}
