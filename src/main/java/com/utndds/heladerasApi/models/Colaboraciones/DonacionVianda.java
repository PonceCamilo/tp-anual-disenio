package com.utndds.heladerasApi.models.Colaboraciones;

import com.utndds.heladerasApi.models.Vianda;
import com.utndds.heladerasApi.models.Colaboradores.Colaborador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DonacionVianda extends Colaboracion {
    Vianda viandaDonada;
    Heladera heladera;
    boolean estado;

    public DonacionVianda(LocalDate fecha, Colaborador colaborador, Vianda viandaDonada, Heladera heladera,
            boolean estado) {
        super(fecha, colaborador);
        this.viandaDonada = viandaDonada;
        this.heladera = heladera;
        this.estado = estado;
    }

    @Override
    public void realizar() {
        System.out.println("COMPLETAR LA DONACION VIANDA");
    }
}
