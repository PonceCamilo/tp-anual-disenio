package com.utndds.heladerasApi.models.Persona.Contacto;

import javax.persistence.*;

@Entity
@DiscriminatorValue("TELEGRAM")
public class Telegram extends Contacto {
    @Column(name = "numero")
    private String numero;

    public Telegram() {
    }

    public Telegram(String numero) {
        this.numero = numero;
    }

    @Override
    public void notificar(String mensaje) {
        System.out.println("SE NOTIFICO CON EXITO AL TELEGRAM: " + this.numero);
    }
}
