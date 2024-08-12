package com.utndds.heladerasApi.models.Heladera;

import java.io.File;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.utndds.heladerasApi.models.Rol.Colaborador;

public class SolicitudApertura {
    Colaborador colaborador;
    Heladera heladera;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private static final String EXCHANGE_NAME = "solicitudApertura";

    public SolicitudApertura(Colaborador colaborador, Heladera heladera) {
        this.colaborador = colaborador;
        this.heladera = heladera;

        this.heladera.agregarSolicitud(this);
        this.iniciarTemporizador();
        this.publicarSolicitud();
    }

    public Colaborador getColaborador() {
        return this.colaborador;
    }

    public void iniciarTemporizador() {
        System.out.println("Temporizador iniciado para abrir: " + heladera.getPunto().getNombre() + " por parte de "
                + colaborador.getPersona().getNombre());

        int tiempo = this.tiempoLimite();

        long delay = TimeUnit.HOURS.toSeconds(tiempo); // Convierte el tiempo en horas a segundos
        scheduler.schedule(this::finalizarTemporizador, delay, TimeUnit.SECONDS);
    }

    private void finalizarTemporizador() {
        this.heladera.eliminarSolicitud(this);
        System.out.println("Temporizador finalizado para abrir: " + heladera.getPunto().getNombre() + " por parte de "
                + colaborador.getPersona().getNombre());
    }

    private int tiempoLimite() {
        int limiteHoras = 0;
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper
                    .readTree(new File(".\\src\\main\\resources\\tiempoAperturaHeladera.json"));
            limiteHoras = rootNode.get("limite_horas").asInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return limiteHoras;
    }

    private void publicarSolicitud() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            factory.setUsername("lautaro_romero_21");
            factory.setPassword("laucha021");
            try (Connection connection = factory.newConnection();
                    Channel channel = connection.createChannel()) {
                channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
                String message = "Heladera: " + heladera.getPunto().getNombre() + " recibio una solicitud de apertura ";
                channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes(StandardCharsets.UTF_8));
                System.out.println(" [x] Sent '" + message + "'");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int tiempo = new SolicitudApertura(null, null).tiempoLimite();
        System.out.println(tiempo);
    }
}
