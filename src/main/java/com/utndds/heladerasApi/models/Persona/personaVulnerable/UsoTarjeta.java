package com.utndds.heladerasApi.models.Persona.personaVulnerable;

import java.time.LocalDate;

import com.utndds.heladerasApi.models.Heladera.Heladera;

public class UsoTarjeta {
    Heladera heladera;
    LocalDate fecha;

    public UsoTarjeta(Heladera heladera, LocalDate fecha) {
        this.heladera = heladera;
        this.fecha = fecha;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

}
