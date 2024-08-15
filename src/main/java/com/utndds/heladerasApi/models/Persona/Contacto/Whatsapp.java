package com.utndds.heladerasApi.models.Persona.Contacto;

import javax.persistence.*;

@Entity
@DiscriminatorValue("WHATSAPP")
public class Whatsapp extends Contacto {
    @Column(name = "numero")
    private String numero;

    public Whatsapp() {
    }

    public Whatsapp(String numero) {
        this.numero = numero;
    }

    @Override
    public void notificar(String mensaje) {
        System.out.println("SE NOTIFICO CON EXITO AL WHATSAPP: " + this.numero);
    }
}
