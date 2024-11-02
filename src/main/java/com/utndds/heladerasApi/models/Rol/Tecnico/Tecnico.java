package com.utndds.heladerasApi.models.Rol.Tecnico;

import java.util.List;

import com.utndds.heladerasApi.models.Heladera.Incidentes.VisitaTecnico;
import com.utndds.heladerasApi.models.Persona.Persona;
import com.utndds.heladerasApi.models.Rol.Rol;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tecnico")
public class Tecnico extends Rol {

    @Column(name = "cuil")
    private String cuil;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "area_cobertura_id")
    @JsonIgnore // Ignorado durante la serialización
    private AreaCobertura areaCobertura;

    @OneToMany(mappedBy = "tecnico", fetch = FetchType.LAZY)
    @JsonManagedReference // Se serializa en Tecnico
    private List<VisitaTecnico> visitas;

    // Constructor vacío para JPA
    public Tecnico() {
    }

    public Tecnico(Persona persona, String cuil, AreaCobertura areaCobertura,
                   List<VisitaTecnico> visitas) {
        super(persona);
        this.cuil = cuil;
        this.areaCobertura = areaCobertura;
        this.visitas = visitas;
    }

}
