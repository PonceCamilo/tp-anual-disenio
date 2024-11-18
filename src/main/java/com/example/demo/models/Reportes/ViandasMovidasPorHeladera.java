package com.example.demo.models.Reportes;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "reporte_viandas_movidas_por_heladera")
public class ViandasMovidasPorHeladera extends Reporte {

    @Column(name = "heladera_id")
    private Long heladeraId;

    @Column(name = "cantidad_viandas")
    private int cantidadViandas;

    protected ViandasMovidasPorHeladera() {
        super();
    }

    public ViandasMovidasPorHeladera(Long heladeraId, int cantidadViandas) {
        this.heladeraId = heladeraId;
        this.cantidadViandas = cantidadViandas;
    }

}