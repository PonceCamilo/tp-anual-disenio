package com.utndds.heladerasApi.models.Persona.Contacto;

import com.utndds.heladerasApi.services.NotificacionApis.WhatsappApi;

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
        String numero = this.valor;
        WhatsappApi.sendTextMessage(mensaje, numero);
    }
}
