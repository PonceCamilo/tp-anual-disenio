package com.utndds.heladerasApi.models.Colaboraciones;

import com.utndds.heladerasApi.models.Colaboradores.Colaborador;
import com.utndds.heladerasApi.models.Heladera;

import java.util.Date;

public class ObtencionHeladera extends Colaboracion{

    Heladera heladera;

    public ObtencionHeladera(Date fecha, Colaborador colaborador, Heladera heladera) {
        super(fecha, colaborador);
        this.heladera = heladera;
    }
}
