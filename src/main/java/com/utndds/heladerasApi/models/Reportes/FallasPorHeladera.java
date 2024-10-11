package com.utndds.heladerasApi.models.Reportes;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

    public FallasPorHeladera(Long heladeraId, int cantidadFallas) {
        this.heladeraId = heladeraId;
        this.cantidadFallas = cantidadFallas;
    }
}