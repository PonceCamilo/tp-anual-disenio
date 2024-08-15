package com.utndds.heladerasApi.models.Heladera.Sensores;

import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.rabbitmq.client.ConnectionFactory;
import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Heladera.Punto;
import com.utndds.heladerasApi.models.Heladera.Incidentes.Alerta;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

import javax.persistence.*;

@Entity
@Table(name = "sensor_temperatura")
public class SensorTemperatura extends Sensor {

    @Column(name = "temperatura")
    private Double temperatura;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private static final String EXCHANGE_NAME = "temperatura";

    // Constructor vacío para JPA
    protected SensorTemperatura() {
    }

    public SensorTemperatura(Heladera heladera) {
        super(heladera);
        scheduler.scheduleAtFixedRate(this::medirTemperatura, 0, 5, TimeUnit.MINUTES); // Corre cada 5 minutos
    }

    public void medirTemperatura() {
        this.obtenerTemperatura();
        this.heladera.actualizarTemperatura(temperatura);
        this.publicarTemperatura();
    }

    private void obtenerTemperatura() {
        try {
            Random random = new Random();
            Double temperaturaNueva = -10 + (20 + 10) * random.nextDouble();
            this.temperatura = temperaturaNueva;
        } catch (Exception e) {
            new Alerta(this.heladera, "Falla en la conexión");
        }
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
                String message = "Heladera: " + this.heladera.getPunto().getNombre() + ", Temp: " + temperatura;
                channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes(StandardCharsets.UTF_8));
                System.out.println(" [x] Sent '" + message + "'");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Punto punto = new Punto(0, 0, "nombre Heladera", "direccion Heladera");
        Heladera heladera = new Heladera(null, punto, 0, 0, 0, false, false, null, null);
        new SensorTemperatura(heladera);
    }
}
