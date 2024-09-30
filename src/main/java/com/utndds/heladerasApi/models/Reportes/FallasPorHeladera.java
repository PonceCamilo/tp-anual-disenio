package com.utndds.heladerasApi.models.Reportes;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "reporte_fallas_por_heladera")
public class FallasPorHeladera extends Reporte {

    @Column(name = "heladera_id")
    private Long heladeraId;

    @Column(name = "cantidad_fallas")
    private int cantidadFallas;

    protected FallasPorHeladera() {
        super();
    }

    public FallasPorHeladera(LocalDate fechaGeneracion, Long heladeraId, int cantidadFallas) {
        super(fechaGeneracion);
        this.heladeraId = heladeraId;
        this.cantidadFallas = cantidadFallas;
    }

    // Getters y Setters
    public Long getHeladeraId() {
        return heladeraId;
    }

    public void setHeladeraId(Long heladeraId) {
        this.heladeraId = heladeraId;
    }

    public int getCantidadFallas() {
        return cantidadFallas;
    }

    public void setCantidadFallas(int cantidadFallas) {
        this.cantidadFallas = cantidadFallas;
    }
}