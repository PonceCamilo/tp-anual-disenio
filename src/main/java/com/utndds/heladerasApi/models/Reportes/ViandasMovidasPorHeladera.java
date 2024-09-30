package com.utndds.heladerasApi.models.Reportes;

import jakarta.persistence.*;

import java.time.LocalDate;

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

    public ViandasMovidasPorHeladera(LocalDate fechaGeneracion, Long heladeraId, int cantidadViandas) {
        super(fechaGeneracion);
        this.heladeraId = heladeraId;
        this.cantidadViandas = cantidadViandas;
    }

    // Getters y Setters
    public Long getHeladeraId() {
        return heladeraId;
    }

    public void setHeladeraId(Long heladeraId) {
        this.heladeraId = heladeraId;
    }

    public int getCantidadViandas() {
        return cantidadViandas;
    }

    public void setCantidadViandas(int cantidadViandas) {
        this.cantidadViandas = cantidadViandas;
    }
}