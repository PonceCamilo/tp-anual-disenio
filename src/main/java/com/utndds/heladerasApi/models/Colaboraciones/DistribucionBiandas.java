package com.utndds.heladerasApi.models.Colaboraciones;

import com.utndds.heladerasApi.models.Colaboradores.Colaborador;
import com.utndds.heladerasApi.models.Heladera;

import java.util.Date;

public class DistribucionBiandas extends Colaboracion{
    Heladera heladeraOrigen;
    Heladera heladeraDestino;
    int cantidadViandasAMover;
    String motivo;

    public DistribucionBiandas(Date fecha, Colaborador colaborador, Heladera heladeraOrigen, Heladera heladeraDestino, int cantidadViandasAMover, String motivo) {
        super(fecha, colaborador);
        this.heladeraOrigen = heladeraOrigen;
        this.heladeraDestino = heladeraDestino;
        this.cantidadViandasAMover = cantidadViandasAMover;
        this.motivo = motivo;
    }
}
