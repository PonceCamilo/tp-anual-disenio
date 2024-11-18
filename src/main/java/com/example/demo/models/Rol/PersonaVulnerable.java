package com.example.demo.models.Rol;

import java.time.LocalDate;

import com.example.demo.models.Persona.Persona;
import com.example.demo.models.Tarjetas.TarjetaPersVuln;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "persona_vulnerable")
public class PersonaVulnerable extends Rol {

    private Long id;
    @Column(name = "fecha_registro")
    private LocalDate fechaRegistro;

    @Column(name = "situacion_calle")
    private boolean situacionCalle;

    @Column(name = "cant_menores_acargo")
    private int cantMenoresAcargo;

    @OneToOne
    @JoinColumn(name = "tarjeta")
    private TarjetaPersVuln tarjeta;

    // Constructor vacío para JPA
    public PersonaVulnerable() {
    }

    public PersonaVulnerable(Persona persona, boolean situacionCalle, int cantMenoresAcargo) {
        super(persona);
        this.fechaRegistro = LocalDate.now();
        this.situacionCalle = situacionCalle;
        this.cantMenoresAcargo = cantMenoresAcargo;
    }

}