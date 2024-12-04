package com.utndds.heladerasApi.models.Persona.Contacto;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("WHATSAPP")
public class Whatsapp extends Contacto {
    public Whatsapp() {
    }

    public Whatsapp(String numero) {
        this.valor = numero;
    }

    @Override
    public void notificar(String mensaje) {
        System.out.println("SE NOTIFICO CON EXITO AL WHATSAPP: " + this.valor);
    }
}
