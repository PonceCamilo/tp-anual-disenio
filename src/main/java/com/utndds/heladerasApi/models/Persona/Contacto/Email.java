package com.utndds.heladerasApi.models.Persona.Contacto;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("EMAIL")
public class Email extends Contacto {

    public Email() {
    }

    public Email(String email) {
        this.valor = email;
    }

    @Override
    public void notificar(String mensaje) {
        System.out.println("SE NOTIFICO CON EXITO AL EMAIL: " + this.valor);
    }
}
