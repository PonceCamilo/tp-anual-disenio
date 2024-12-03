package com.utndds.heladerasApi.models.Persona.Contacto;

import jakarta.persistence.*;
import com.utndds.heladerasApi.services.NotificacionApis.TelegramApi;
@Entity
@DiscriminatorValue("TELEGRAM")
public class Telegram extends Contacto {

    private String chatId;

    public Telegram() {
    }

    public Telegram(String chatId) {
        this.chatId = chatId;
    }

    @Override
    public void notificar(String mensaje) {
        String userId = this.chatId;
        TelegramApi.sendTextMessage(mensaje , userId);
    }
}
