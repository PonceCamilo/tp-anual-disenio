package com.example.demo.models.Reportes;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "reporte_viandas_movidas_por_colaborador")
public class ViandasMovidasPorColaborador extends Reporte {

    @Column(name = "colaborador_id")
    private Long colaboradorId;

    @Column(name = "cantidad_viandas")
    private int cantidadViandas;

    protected ViandasMovidasPorColaborador() {
        super();
    }

    public ViandasMovidasPorColaborador(Long colaboradorId, int cantidadViandas) {
        this.colaboradorId = colaboradorId;
        this.cantidadViandas = cantidadViandas;
    }

}
