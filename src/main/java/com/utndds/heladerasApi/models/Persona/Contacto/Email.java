package com.utndds.heladerasApi.models.Persona.Contacto;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("EMAIL")
public class Email extends Contacto {
    @Column(name = "email")
    private String email;

    public Email() {
    }

    public Email(String email) {
        this.email = email;
    }

    @Override
    public void notificar(String mensaje) {
        System.out.println("SE NOTIFICO CON EXITO AL EMAIL: " + this.email);
    }
}
