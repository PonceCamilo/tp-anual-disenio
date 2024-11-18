package com.example.demo.models.Rol;

import com.example.demo.models.Persona.Persona;
import com.example.demo.models.Persona.Contacto.Contacto;
import com.example.demo.models.Rol.Tecnico.Tecnico;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Tecnico.class, name = "tecnico"),
        @JsonSubTypes.Type(value = Colaborador.class, name = "colaborador")
})
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

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "persona")
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
