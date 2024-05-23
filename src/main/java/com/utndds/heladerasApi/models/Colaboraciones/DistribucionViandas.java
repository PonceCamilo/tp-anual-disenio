package com.utndds.heladerasApi.models.Colaboraciones;

import com.utndds.heladerasApi.models.Colaboradores.Colaborador;
import com.utndds.heladerasApi.models.Heladera;

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
        System.out.println("COMPLETAR LA DISTRIBUCION VIANDAS");
    }
}
