package com.example.demo.models.Colaboraciones;

import com.example.demo.models.Heladera.Heladera;
import com.example.demo.models.Rol.Colaborador;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class DistribucionViandas extends Colaboracion {

    @ManyToOne
    @JoinColumn(name = "heladera_origen")
    private Heladera heladeraOrigen;

    @ManyToOne
    @JoinColumn(name = "heladera_destino")
    private Heladera heladeraDestino;

    @Column(name = "cantidad_viandas_a_mover")
    private int cantidadViandasAMover;

    @Column(name = "motivo")
    private String motivo;

    // Constructor vacío para JPA
    public DistribucionViandas() {
    }

    public DistribucionViandas(Colaborador colaborador, Heladera heladeraOrigen,
            Heladera heladeraDestino, int cantidadViandasAMover, String motivo) {
        super(colaborador);
        this.heladeraOrigen = heladeraOrigen;
        this.heladeraDestino = heladeraDestino;
        this.cantidadViandasAMover = cantidadViandasAMover;
        this.motivo = motivo;
    }
}
