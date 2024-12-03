package com.utndds.heladerasApi.services.NotificacionApis;

import okhttp3.*;
import java.io.IOException;

public class TelegramApi {
    private static final String TELEGRAM_API_BASE = "https://api.telegram.org/bot";
    private static final String TOKEN = "7922716492:AAGukadJbEGhVXR9N1bREzeiqu3lwHAzrn0";
    private static final OkHttpClient CLIENT = new OkHttpClient();
    public static void sendTextMessage(String message, String chatId) {
        String url = TELEGRAM_API_BASE + TOKEN + "/sendMessage";
        String jsonBody = String.format("{\"chat_id\": \"%s\", \"text\": \"%s\", \"parse_mode\": \"HTML\"}", chatId, message);

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(jsonBody, MediaType.get("application/json")))
                .build();

        try (Response response = CLIENT.newCall(request).execute()) {
            if (response.isSuccessful()) {
                System.out.println("Mensaje enviado exitosamente.");
            } else {
                System.err.println("Error al enviar: " + response.code() + " - " + response.body().string());
            }
        } catch (IOException e) {
            System.err.println("Error de conexión:");
            e.printStackTrace();
        }
    }
}
