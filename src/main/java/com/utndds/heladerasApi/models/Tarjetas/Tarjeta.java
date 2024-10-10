package com.utndds.heladerasApi.models.Tarjetas;

import com.utndds.heladerasApi.models.Rol.Rol;
import jakarta.persistence.*;
import lombok.Getter;
import java.util.List;
import java.util.ArrayList;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Tarjeta {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id; // Campo identificador

    @Getter
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

    public void setDueño(Rol dueño) {
        this.dueño = dueño;
    }
}
