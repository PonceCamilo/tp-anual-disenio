package com.utndds.heladerasApi.models.Heladera.Incidentes.Incidente;

import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Rol.Colaborador;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "falla_tecnica")
public class FallaTecnica extends Incidente {
    @ManyToOne
    @JoinColumn(name = "colaborador", nullable = false) // Assuming you want to set up a foreign key relationship
    private Colaborador colaborador;

    @Column(name = "descripcion")
    private String descripcion; // Description of the incident

    @Column(name = "foto")
    private String foto; // Path or URL to the photo

    // Default constructor for JPA
    public FallaTecnica() {
    }

    // Constructor with parameters
    public FallaTecnica(Heladera heladera, Colaborador colaborador, String descripcion, String foto) {
        super(heladera);
        this.colaborador = colaborador;
        this.descripcion = descripcion;
        this.foto = foto;
    }

}
