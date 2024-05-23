package com.utndds.heladerasApi.models.Colaboraciones;

import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Heladera.Vianda;
import com.utndds.heladerasApi.models.Persona.Colaboradores.Colaborador;

import java.time.LocalDate;

public class DonacionVianda extends Colaboracion {
    Vianda viandaDonada;
    Heladera heladera;
    boolean estado;
    double coeficiente;

    public DonacionVianda(LocalDate fecha, Colaborador colaborador, Vianda viandaDonada, Heladera heladera,
            boolean estado) {
        super(fecha, colaborador);
        this.viandaDonada = viandaDonada;
        this.heladera = heladera;
        this.estado = estado;
    }

    @Override
    public void realizar() {
        System.out.println("SE REALIZO LA DONACION DE VIANDA");
    }

    @Override
    public double puntosGanados() {
        return this.obtenerCoeficiente();
    }

    @Override
    protected double obtenerCoeficiente() {
        System.out.println("COMPLETAR COMO OBTENER COEFICIENTE");
        return 23;
    }

}
