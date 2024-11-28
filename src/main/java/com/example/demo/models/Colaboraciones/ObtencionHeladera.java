package com.example.demo.models.Colaboraciones;

import java.time.LocalDate;

import com.example.demo.models.Heladera.Heladera;

import com.example.demo.models.Rol.Colaborador;
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
        this.fecha = LocalDate.now();
    }

    public ObtencionHeladera(Colaborador colaborador, Heladera heladera) {
        super(colaborador);
        this.heladera = heladera;
    }

}
