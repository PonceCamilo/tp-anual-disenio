package com.utndds.heladerasApi.models.Tarjetas.TarjetaPersVuln;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Rol.PersonaVulnerable;
import com.utndds.heladerasApi.models.Tarjetas.Tarjeta;

import java.time.Duration;

public class TarjetaPersVuln extends Tarjeta {
    String codigo;
    PersonaVulnerable persVul;
    int cantUsosHoy;
    List<UsoHeladera> usos = new ArrayList<>();

    public TarjetaPersVuln(String codigo) {
        this.codigo = codigo;
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
        System.out.println("Se reinició el contador de usos diarios de la tarjeta " + this.codigo);
    }

    public boolean puedeUsarse(Heladera heladera) {
        return this.cantUsosHoy < this.extraccionesDiariasPermitidas();
    }

    private int extraccionesDiariasPermitidas() {
        return 4 + 2 * this.persVul.getCantMenoresAcargo();
    }

    protected void usar(Heladera heladera) {
        new UsoHeladera(heladera, this);
    }

    public void agregarUso(UsoHeladera uso) {
        this.usos.add(uso);
        this.cantUsosHoy++;
    }

    public void setPersVul(PersonaVulnerable persVul) {
        this.persVul = persVul;
    }

    public String getCodigo() {
        return this.codigo;
    }
}
