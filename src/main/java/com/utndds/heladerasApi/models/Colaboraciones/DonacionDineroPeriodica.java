package com.utndds.heladerasApi.models.Colaboraciones;

import com.utndds.heladerasApi.models.Colaboradores.Colaborador;

import java.util.Date;

public class DonacionDineroPeriodica extends DonacionDinero{
    int frecuencia;

    public DonacionDineroPeriodica(Date fecha, Colaborador colaborador, double monto, int frecuencia) {
        super(fecha, colaborador, monto);
        this.frecuencia = frecuencia;
    }
}
