package com.utndds.heladerasApi.models.Solicitudes;

import java.io.File;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.ONG.ONG;
import com.utndds.heladerasApi.models.Rol.Colaborador;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "solicitud_apertura")
public class SolicitudApertura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private Long id;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ong") // Nombre de la columna que se refiere a la ONG
    private ONG ong;
    @Transient
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    @Transient
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

    public static void main(String[] args) {
        int tiempo = new SolicitudApertura(null, null, null).tiempoLimite();
        System.out.println(tiempo);
    }
}
