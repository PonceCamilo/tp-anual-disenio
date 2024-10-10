package com.utndds.heladerasApi.models.Rol;

import com.utndds.heladerasApi.models.Persona.Persona;
import com.utndds.heladerasApi.models.Persona.Contacto.Contacto;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

@Setter
@Getter
@Entity
@Inheritance(strategy = InheritanceType.JOINED) // Estrategia de herencia
@Table(name = "rol")
public abstract class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;

    @ManyToOne(fetch = FetchType.LAZY)
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
