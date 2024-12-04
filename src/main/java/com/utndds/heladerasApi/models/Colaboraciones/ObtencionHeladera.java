package com.utndds.heladerasApi.models.Colaboraciones;

import com.utndds.heladerasApi.models.Heladera.Heladera;

import com.utndds.heladerasApi.models.Rol.Colaborador;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class ObtencionHeladera extends Colaboracion {

    @ManyToOne
    @JoinColumn(name = "heladera")
    private Heladera heladera;

    // Constructor vacío para JPA
    public ObtencionHeladera() {
    }

    public ObtencionHeladera(Colaborador colaborador, Heladera heladera) {
        super(colaborador);
        this.heladera = heladera;
    }

}
