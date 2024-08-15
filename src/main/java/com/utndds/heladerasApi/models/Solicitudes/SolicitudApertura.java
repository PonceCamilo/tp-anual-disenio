package com.utndds.heladerasApi.models.Solicitudes;

import java.io.File;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.ONG.ONG;
import com.utndds.heladerasApi.models.Rol.Colaborador;
import javax.persistence.*;

@Entity
@Table(name = "solicitud_apertura")
public class SolicitudApertura {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "colaborador")
    private Colaborador colaborador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "heladera")
    private Heladera heladera;

    @Column(name = "fecha_hora")
    private LocalDateTime fechaHora;

    @Column(name = "motivo")
    private String motivo;

    @Column(name = "estado")
    private boolean estado;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private static final String EXCHANGE_NAME = "solicitudApertura";

    // Constructor vacío para JPA
    public SolicitudApertura() {
    }

    public SolicitudApertura(Colaborador colaborador, Heladera heladera, String motivo) {
        this.colaborador = colaborador;
        this.heladera = heladera;
        this.motivo = motivo;
        this.fechaHora = LocalDateTime.now();
        this.estado = true;

        this.procesar();
    }

    private void procesar() {
        ONG.getInstance().agregarSolicitud(this);
        this.iniciarTemporizador();
        this.publicarSolicitud();
    }

    public void iniciarTemporizador() {
        System.out.println("Temporizador iniciado para abrir: " + heladera.getPunto().getNombre() + " por parte de "
                + colaborador.getPersona().getNombre());

        int tiempo = this.tiempoLimite();

        long delay = TimeUnit.HOURS.toSeconds(tiempo);
        scheduler.schedule(this::finalizarTemporizador, delay, TimeUnit.SECONDS);
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

    private void finalizarTemporizador() {
        this.estado = false;
        System.out.println("Temporizador finalizado para abrir: " + heladera.getPunto().getNombre() + " por parte de "
                + colaborador.getPersona().getNombre());
    }

    public Colaborador getColaborador() {
        return this.colaborador;
    }

    public boolean getEstado() {
        return this.estado;
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
        int tiempo = new SolicitudApertura(null, null, null).tiempoLimite();
        System.out.println(tiempo);
    }
}
