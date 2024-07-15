package com.utndds.heladerasApi.models.Tarjeta;

import com.utndds.heladerasApi.models.Heladera.Heladera;
import java.time.LocalDate;

public class UsoTarjeta {
    Heladera heladera;
    LocalDate fechaUso;

    public UsoTarjeta(Heladera heladera, LocalDate fechaUso) {
        this.heladera = heladera;
        this.fechaUso = fechaUso;
    }

    public void setFechaUso(LocalDate fechaUso) {
        this.fechaUso = fechaUso;
    }

    public LocalDate getFechaUso() {
        return fechaUso;
    }

    public void setHeladera(Heladera heladera) {
        this.heladera = heladera;
    }

    public Heladera getHeladera() {
        return heladera;
    }

}
