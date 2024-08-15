package com.utndds.heladerasApi.models.Persona;

import java.util.ArrayList;
import java.util.List;

import com.utndds.heladerasApi.models.Persona.Contacto.Contacto;

import lombok.Getter;

import javax.persistence.*;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(name = "direccion")
    private String direccion;

    @Getter
    @OneToMany(mappedBy = "persona")
    private List<Contacto> mediosContacto = new ArrayList<>();

    // Constructor vacío para JPA
    protected Persona() {
    }

    public Persona(String direccion, List<Contacto> mediosContacto) {
        this.direccion = direccion;
        this.mediosContacto = mediosContacto;
    }

    public abstract String getNombre();

    public void notificar(String message) {
        for (Contacto medio : this.mediosContacto) {
            medio.notificar(message);
        }
    }
}
