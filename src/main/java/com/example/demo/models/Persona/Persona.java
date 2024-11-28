package com.example.demo.models.Persona;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.models.Persona.Contacto.Contacto;
import com.example.demo.models.Rol.Rol;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Persona {

    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore // Marcamos esta como la referencia principal
    private List<Rol> roles = new ArrayList<>();

    @Column(name = "direccion")
    private String direccion;

    @OneToMany(mappedBy = "persona", fetch = FetchType.EAGER)
    private List<Contacto> mediosContacto = new ArrayList<>();

    // Constructor vacío para JPA
    public Persona() {
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
