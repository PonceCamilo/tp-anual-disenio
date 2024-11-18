package com.example.demo.models.Tarjetas;

import com.example.demo.models.Rol.Rol;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.ArrayList;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Tarjeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id; // Campo identificador

    @OneToOne
    @JoinColumn(name = "dueño")
    protected Rol dueño;

    @OneToMany(mappedBy = "tarjeta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Apertura> aperturas = new ArrayList<>();

    public Tarjeta() {
    }

    public Tarjeta(Rol dueño) {
        this.dueño = dueño;
    }
}
