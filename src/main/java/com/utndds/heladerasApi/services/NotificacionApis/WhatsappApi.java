package com.utndds.heladerasApi.services.NotificacionApis;

import java.io.IOException;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class WhatsappApi {
   private static final String BASE_URL = "https://api.ultramsg.com/instance101132/messages/chat";//cambiar esto 
    private static final String API_TOKEN = "n1fxbmpplr55ay0m"; // cambiar esto
    private static final OkHttpClient CLIENT = new OkHttpClient();

    public static void sendTextMessage(String mensaje ,String numero) {
        RequestBody body = new FormBody.Builder()
                .add("token", API_TOKEN)
                .add("to", "+549"+ numero) // numero del receptor
                .add("body", mensaje) //agregar el msj que necesitemos 
                .build();

        // Crear la solicitud HTTP
        Request request = new Request.Builder()
                .url(BASE_URL)
                .post(body)
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .build();

        try (Response response = CLIENT.newCall(request).execute()) {
            if (response.isSuccessful()) {
                System.out.println("Mensaje enviado exitosamente: " + response.body().string());
            } else {
                System.err.println("Error al enviar mensaje: " + response.code() + " - " + response.body().string());
            }
        } catch (IOException e) {
            System.err.println("Excepción al enviar el mensaje:");
            e.printStackTrace();
        }
    } 
}
