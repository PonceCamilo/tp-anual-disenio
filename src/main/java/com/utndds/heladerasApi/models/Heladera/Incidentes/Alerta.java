package com.utndds.heladerasApi.models.Heladera.Incidentes;

import java.nio.charset.StandardCharsets;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Heladera.Punto;
import javax.persistence.*;

@Entity
@Table(name = "alerta")
public class Alerta extends Incidente {

    private static final String EXCHANGE_NAME = "alerta";

    @Column(name = "tipo")
    private String tipo;

    // Constructor vacío para JPA
    public Alerta() {
    }

    public Alerta(Heladera heladera, String tipo) {
        super(heladera);
        this.tipo = tipo;
        this.procesar();
        this.publicarAlerta();
    }

    public void procesar() {
        super.procesar();
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
                String message = "Heladera: " + heladera.getPunto().getNombre() + ", alerta";
                channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes(StandardCharsets.UTF_8));
                System.out.println(" [x] Sent '" + message + "'");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Punto punto = new Punto(1, 1, "nombre heladera", null);
        Heladera heladera = new Heladera(null, punto, 0, 0, 0, false, false, null, null);
        new Alerta(heladera, null);
    }

}
