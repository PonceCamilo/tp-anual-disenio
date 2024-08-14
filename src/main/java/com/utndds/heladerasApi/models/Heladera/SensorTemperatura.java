package com.utndds.heladerasApi.models.Heladera;

import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class SensorTemperatura {
    Heladera heladera;
    double temperatura;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private static final String EXCHANGE_NAME = "temperatura";

    public SensorTemperatura(Heladera heladera, double temperatura) {
        this.heladera = heladera;
        this.temperatura = temperatura;
        scheduler.scheduleAtFixedRate(this::calcularTemp, 0, 5, TimeUnit.SECONDS);// se ejecuta c/5mins
    }

    private void calcularTemp() {
        Random random = new Random();
        this.temperatura = -10 + (20 + 10) * random.nextDouble();
        heladera.setUltimaTempRegistrada(this.temperatura);
        heladera.actualizar();
        this.publicarTemperatura();
    }

    private void publicarTemperatura() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            factory.setUsername("lautaro_romero_21"); // Cambia esto si tu usuario es diferente
            factory.setPassword("laucha021"); // Cambia esto si tu contraseña es diferente
            try (Connection connection = factory.newConnection();
                    Channel channel = connection.createChannel()) {
                channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
                String message = "Heladera: " + heladera.getNombre() + ", Temp: " + temperatura;
                channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes(StandardCharsets.UTF_8));
                System.out.println(" [x] Sent '" + message + "'");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Heladera heladera = new Heladera("nombre", null, 0, null, 0, 0, 0, false, null, null, null);
        new SensorTemperatura(heladera, 0);
    }
}
