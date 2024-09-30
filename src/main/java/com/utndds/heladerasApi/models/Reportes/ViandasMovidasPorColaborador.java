package com.utndds.heladerasApi.models.Reportes;

import jakarta.persistence.*;

import java.time.LocalDate;

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

    public ViandasMovidasPorColaborador(LocalDate fechaGeneracion, Long colaboradorId, int cantidadViandas) {
        super(fechaGeneracion);
        this.colaboradorId = colaboradorId;
        this.cantidadViandas = cantidadViandas;
    }

    // Getters y Setters
    public Long getColaboradorId() {
        return colaboradorId;
    }

    public void setColaboradorId(Long colaboradorId) {
        this.colaboradorId = colaboradorId;
    }

    public int getCantidadViandas() {
        return cantidadViandas;
    }

    public void setCantidadViandas(int cantidadViandas) {
        this.cantidadViandas = cantidadViandas;
    }
}
