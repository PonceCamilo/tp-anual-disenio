package com.utndds.heladerasApi.models;

import java.time.LocalDate;

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
