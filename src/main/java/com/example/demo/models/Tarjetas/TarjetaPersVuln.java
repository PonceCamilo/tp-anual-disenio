package com.example.demo.models.Tarjetas;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.example.demo.models.Heladera.Heladera;
import com.example.demo.models.Rol.PersonaVulnerable;

import jakarta.persistence.*;

import java.time.Duration;

@Entity
public class TarjetaPersVuln extends Tarjeta {

    @Column(name = "cant_usos_hoy")
    private int cantUsosHoy;

    public TarjetaPersVuln() {
        this.cantUsosHoy = 0;
    }

    public TarjetaPersVuln(PersonaVulnerable personaVulnerable) {
        super(personaVulnerable);
        this.cantUsosHoy = 0;

        this.programarReinicioDeUsos();
    }

    private void programarReinicioDeUsos() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime medianoche = ahora.toLocalDate().atStartOfDay().plusDays(1);
        Duration durationUntilMidnight = Duration.between(ahora, medianoche);
        long initialDelay = durationUntilMidnight.getSeconds();

        scheduler.scheduleAtFixedRate(
                this::reiniciarUsosPermitidos,
                initialDelay,
                TimeUnit.DAYS.toSeconds(1),
                TimeUnit.SECONDS);
    }

    private void reiniciarUsosPermitidos() {
        this.cantUsosHoy = 0;
        System.out.println("Se reinició el contador de usos diarios de la tarjeta " + this.id);
    }

    public boolean puedeUsarse(Heladera heladera) {
        return this.cantUsosHoy < this.extraccionesDiariasPermitidas();
    }

    private int extraccionesDiariasPermitidas() {
        return 4 + 2 * this.dueño.getCantMenoresAcargo();
    }
}
