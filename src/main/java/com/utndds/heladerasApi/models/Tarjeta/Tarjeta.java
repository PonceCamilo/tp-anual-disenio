package com.utndds.heladerasApi.models.Tarjeta;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Rol.PersonaVulnerable;
import java.time.Duration;

public class Tarjeta {
    int cantUsosHoy;
    PersonaVulnerable persVul;
    List<UsoTarjeta> usos = new ArrayList<>();

    public Tarjeta(PersonaVulnerable persVul, int cantUsosHoy, List<UsoTarjeta> usos) {
        this.persVul = persVul;
        this.cantUsosHoy = cantUsosHoy;
        this.usos = usos;

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
        System.out.println("Se reinició el contador de usos diarios a las 00:00.");
    }

    public void usar(Heladera heladera) {
        if (heladera.cantViandasDentro() > 0) {
            if (this.puedeUsarse()) {
                this.usos.add(new UsoTarjeta(heladera, LocalDate.now()));
                this.cantUsosHoy++;
                System.out.println("La persona vulnerable: " + this.persVul + " sacó una vianda");
            } else {
                System.out.println("No puede usar la tarjeta (exceso de usos diarios)");
            }
        } else {
            System.out.println("No hay viandas en la heladera");
        }
    }

    private boolean puedeUsarse() {
        return this.cantUsosHoy < this.persVul.extraccionesDiariasPermitidas();
    }

}
