package com.example.demo.models.Colaboraciones.DonacionViandas;

import com.example.demo.models.Colaboraciones.Colaboracion;
import com.example.demo.models.Heladera.Heladera;
import com.example.demo.models.Rol.Colaborador;

import jakarta.persistence.*;

@Entity
public class DonacionVianda extends Colaboracion {

    @OneToOne(cascade = CascadeType.ALL)
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
