package com.example.demo.models.Rol;

import com.example.demo.models.Persona.Persona;
import com.example.demo.models.Persona.Contacto.Contacto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

@Setter
@Getter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "rol")
public abstract class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;

    @Column(name = "UUID", unique = true)
    protected String UUID;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "persona")
    @JsonBackReference
    protected Persona persona;

    // Constructor vacío para JPA
    protected Rol() {
    }

    public Rol(Persona persona) {
        this.persona = persona;
    }

    public void notificar(String mensaje) {
        for (Contacto medio : this.persona.getMediosContacto()) {
            medio.notificar(mensaje);
        }
    }

    public int getCantMenoresAcargo() {
        return 0;
    }
}
