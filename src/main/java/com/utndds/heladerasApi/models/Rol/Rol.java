package com.utndds.heladerasApi.models.Rol;

import com.utndds.heladerasApi.models.Persona.Persona;
import com.utndds.heladerasApi.models.Persona.Contacto.Contacto;

import lombok.Getter;

import javax.persistence.*;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Rol {

    @Getter
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

}
