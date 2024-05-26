package com.utndds.heladerasApi.models.Colaboraciones;

import java.time.LocalDate;

import com.utndds.heladerasApi.models.Persona.Colaboradores.Colaborador;

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
        System.out.println("SE REALIZO LA DONACION DE DINERO");
    }

    @Override
    public double puntosGanados() {
        return this.monto * this.obtenerCoeficiente();
    }

    @Override
    protected double obtenerCoeficiente() {
        System.out.println("COMPLETAR COMO OBTENER COEFICIENTE");
        return 23;
    }
}
