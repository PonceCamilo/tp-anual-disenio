package com.utndds.heladerasApi.models.Colaboraciones.DonacionViandas;

import com.utndds.heladerasApi.models.Colaboraciones.Colaboracion;
import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Rol.Colaborador;

import jakarta.persistence.*;

@Entity
public class DonacionVianda extends Colaboracion {

    @OneToOne
    @JoinColumn(name = "vianda")
    private Vianda viandaDonada;

    @ManyToOne
    @JoinColumn(name = "heladera")
    private Heladera heladera;

    // Constructor vacío para JPA
    public DonacionVianda() {
    }

    public DonacionVianda(Colaborador colaborador,
            Vianda viandaDonada, Heladera heladera) {
        super(colaborador);
        this.viandaDonada = viandaDonada;
        this.heladera = heladera;
    }

}
