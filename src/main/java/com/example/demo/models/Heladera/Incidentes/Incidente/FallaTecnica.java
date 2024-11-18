package com.example.demo.models.Heladera.Incidentes.Incidente;

import com.example.demo.models.Heladera.Heladera;
import com.example.demo.models.Rol.Colaborador;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "falla_tecnica")
public class FallaTecnica extends Incidente {
    @ManyToOne
    @JoinColumn(name = "colaborador")
    private Colaborador colaborador;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "foto")
    private String foto;

    // Default constructor for JPA
    public FallaTecnica() {
    }

    public FallaTecnica(Heladera heladera, Colaborador colaborador, String descripcion, String foto) {
        super(heladera);
        this.colaborador = colaborador;
        this.descripcion = descripcion;
        this.foto = foto;
    }

}
