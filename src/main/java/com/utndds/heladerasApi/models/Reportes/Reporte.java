package com.utndds.heladerasApi.models.Reportes;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // Estrategia para tener tablas separadas por reporte
@Table(name = "reporte")
public abstract class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_generacion")
    private LocalDate fechaGeneracion;

    protected Reporte() {
        this.fechaGeneracion = LocalDate.now();
    }

}
