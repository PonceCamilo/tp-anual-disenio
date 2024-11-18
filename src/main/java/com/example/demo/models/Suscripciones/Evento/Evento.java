package com.example.demo.models.Suscripciones.Evento;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.models.Heladera.Heladera;
import com.example.demo.models.Suscripciones.Suscripcion;
import com.example.demo.models.Persona.Contacto.Contacto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_evento")
public abstract class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Suscripcion") // Nombre de la columna que se refiere a la ONG
    protected Suscripcion suscripcion;

    @ManyToMany
    @JoinTable(name = "evento_contacto", joinColumns = @JoinColumn(name = "evento"), inverseJoinColumns = @JoinColumn(name = "contacto"))
    protected List<Contacto> mediosDeseados = new ArrayList<>();

    // Constructor vacío para JPA
    public Evento() {
    }

    public Evento(List<Contacto> mediosDeseados) {
        this.mediosDeseados = mediosDeseados;
    }

    public abstract void verificarEvento(Heladera heladera);

    protected abstract void notificarEvento(Heladera heladera);
}
