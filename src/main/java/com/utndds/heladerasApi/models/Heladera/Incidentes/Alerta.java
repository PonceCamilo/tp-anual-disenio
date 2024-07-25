package com.utndds.heladerasApi.models.Heladera.Incidentes;

import java.nio.charset.StandardCharsets;
import java.sql.Time;
import java.time.LocalDate;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.utndds.heladerasApi.models.Heladera.Heladera;

public class Alerta extends Incidente {
    private static final String EXCHANGE_NAME = "alerta";

    public Alerta(LocalDate fecha, Time hora, Heladera heladera) {
        super(fecha, hora, heladera);
        this.publicarAlerta();
    }

    @Override
    public void procesar() {

    };

    private void publicarAlerta() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            factory.setUsername("lautaro_romero_21");
            factory.setPassword("laucha021");
            try (Connection connection = factory.newConnection();
                    Channel channel = connection.createChannel()) {
                channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
                String message = "Heladera: " + heladera.getNombre() + ", alerta";
                channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes(StandardCharsets.UTF_8));
                System.out.println(" [x] Sent '" + message + "'");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Heladera heladera = new Heladera("nombre", null, 0, null, 0, 0, 0, false, null, null, null);
        new Alerta(null, null, heladera);
    }

}
