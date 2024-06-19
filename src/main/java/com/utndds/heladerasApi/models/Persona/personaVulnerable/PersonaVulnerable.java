package com.utndds.heladerasApi.models.Persona.personaVulnerable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Persona.Documento;

public class PersonaVulnerable {
    String nombre;
    LocalDate fechaNacimiento;
    LocalDate fechaRegistro;
    boolean situacionCalle;
    String domicilio;
    Documento documento;
    int cantMenoresAcargo;
    List<UsoTarjeta> usosTarjeta = new ArrayList<>();
    int cantUsosHoy = 0;
    Timer temporizador = new Timer(true); // Hilo de demonio

    public PersonaVulnerable(
            String nombre,
            LocalDate fechaNacimiento,
            LocalDate fechaRegistro,
            boolean situacionCalle,
            String domicilio,
            Documento documento,
            int cantMenoresAcargo,
            List<UsoTarjeta> usosTarjeta) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaRegistro = fechaRegistro;
        this.situacionCalle = situacionCalle;
        this.domicilio = domicilio;
        this.documento = documento;
        this.cantMenoresAcargo = cantMenoresAcargo;
        this.usosTarjeta = usosTarjeta;

        programarReinicioDeUsos();
    }

    public void usarHeladera(Heladera heladera) {
        if (heladera.cantViandasDentro() > 0) {
            if (this.puedeUsarTarjeta()) {
                System.out.println("La persona vulnerable: " + this.nombre + " sacó una vianda");
                this.usosTarjeta.add(new UsoTarjeta(heladera, LocalDate.now()));
                this.cantUsosHoy++;
            } else {
                System.out.println("No puede usar la tarjeta (exceso de usos diarios)");
            }
        } else {
            System.out.println("No hay viandas en la heladera");
        }
    }

    private boolean puedeUsarTarjeta() {
        return this.cantUsosHoy < this.cantUsosDiariosPermitidos();
    }

    private int cantUsosDiariosPermitidos() {
        return 4 + 2 * this.cantMenoresAcargo;
    }

    private void reiniciarUsosPermitidos() {
        this.cantUsosHoy = 0;
        System.out.println("Se reinició el contador de usos diarios a las 00:00.");
    }

    private void programarReinicioDeUsos() {
        TimerTask tareaReinicio = new TimerTask() {
            @Override
            public void run() {
                reiniciarUsosPermitidos();
            }
        };

        // Calcular el tiempo hasta la próxima medianoche
        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime proximaMedianoche = LocalDateTime.of(ahora.toLocalDate().plusDays(1), LocalTime.MIDNIGHT);
        long retrasoInicial = Duration.between(ahora, proximaMedianoche).toMillis();

        // Programar la tarea para que se ejecute a medianoche todos los días
        temporizador.scheduleAtFixedRate(tareaReinicio, retrasoInicial, 24 * 60 * 60 * 1000);
    }
}