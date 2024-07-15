package com.utndds.heladerasApi.models.Heladera;

import java.io.File;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utndds.heladerasApi.models.Rol.Colaborador;

public class SolicitudApertura {
    Colaborador colaborador;
    Heladera heladera;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public SolicitudApertura(Colaborador colaborador, Heladera heladera) {
        this.colaborador = colaborador;
        this.heladera = heladera;

        this.heladera.agregarSolicitud(this);
        this.iniciarTemporizador();
    }

    public Colaborador getColaborador() {
        return this.colaborador;
    }

    public void iniciarTemporizador() {
        System.out.println("Temporizador iniciado para abrir: " + heladera.getNombre() + " por parte de "
                + colaborador.getPersona().getNombre());

        int tiempo = this.tiempoLimite();

        long delay = TimeUnit.HOURS.toSeconds(tiempo); // Convierte el tiempo en horas a segundos
        scheduler.schedule(this::finalizarTemporizador, delay, TimeUnit.SECONDS);
    }

    private void finalizarTemporizador() {
        this.heladera.eliminarSolicitud(this);
        System.out.println("Temporizador finalizado para abrir: " + heladera.getNombre() + " por parte de "
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

    public static void main(String[] args) {
        int tiempo = new SolicitudApertura(null, null).tiempoLimite();
        System.out.println(tiempo);
    }
}
