package com.utndds.heladerasApi.models.Colaboraciones;

import com.utndds.heladerasApi.models.Colaboradores.Colaborador;
import com.utndds.heladerasApi.models.Heladera;

import java.time.LocalDate;

public class ObtencionHeladera extends Colaboracion {

    Heladera heladera;

    public ObtencionHeladera(LocalDate fecha, Colaborador colaborador, Heladera heladera) {
        super(fecha, colaborador);
        this.heladera = heladera;
    }

    @Override
    public void realizar() {
        System.out.println("COMPLETAR LA  OBTENCION HELADERA");
    }
}
