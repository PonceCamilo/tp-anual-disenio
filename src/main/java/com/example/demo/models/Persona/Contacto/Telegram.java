package com.example.demo.models.Persona.Contacto;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("TELEGRAM")
public class Telegram extends Contacto {

    public Telegram() {
    }

    public Telegram(String numero) {
        this.valor = numero;
    }

    @Override
    public void notificar(String mensaje) {
        System.out.println("SE NOTIFICO CON EXITO AL TELEGRAM: " + this.valor);
    }
}
