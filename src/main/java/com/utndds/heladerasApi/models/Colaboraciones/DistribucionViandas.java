package com.utndds.heladerasApi.models.Colaboraciones;

import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Persona.Colaboradores.Colaborador;

import java.time.LocalDate;

public class DistribucionViandas extends Colaboracion {
    Heladera heladeraOrigen;
    Heladera heladeraDestino;
    int cantidadViandasAMover;
    String motivo;

    public DistribucionViandas(LocalDate fecha, Colaborador colaborador, Heladera heladeraOrigen,
            Heladera heladeraDestino, int cantidadViandasAMover, String motivo) {
        super(fecha, colaborador);
        this.heladeraOrigen = heladeraOrigen;
        this.heladeraDestino = heladeraDestino;
        this.cantidadViandasAMover = cantidadViandasAMover;
        this.motivo = motivo;
    }

    @Override
    public void realizar() {
        System.out.println("SE REALIZO LA DISTRIBUCION VIANDAS");
    }

    @Override
    public double puntosGanados() {
        return this.cantidadViandasAMover * this.obtenerCoeficiente();
    }

    @Override
    protected double obtenerCoeficiente() {
        System.out.println("COMPLETAR COMO OBTENER COEFICIENTE");
        return 23;
    }
}
