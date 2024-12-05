package com.utndds.heladerasApi.models.Persona.Contacto;

import jakarta.persistence.*;
import com.utndds.heladerasApi.services.NotificacionApis.TelegramApi;

@Entity
@DiscriminatorValue("TELEGRAM")
public class Telegram extends Contacto {

    public Telegram() {
    }

    public Telegram(String chatId) {
        this.valor = chatId;
    }

    @Override
    public void notificar(String mensaje) {
        String userId = this.valor;
        TelegramApi.sendTextMessage(mensaje, userId);
    }
}
