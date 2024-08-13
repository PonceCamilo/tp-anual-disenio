package com.utndds.heladerasApi.models.Tarjetas.TarjetaPersVuln;

import com.utndds.heladerasApi.models.Heladera.Heladera;

import java.time.LocalDate;

public class UsoHeladera {
    Heladera heladera;
    TarjetaPersVuln tarjeta;
    LocalDate fechaUso;

    public UsoHeladera(Heladera heladera, TarjetaPersVuln tarjeta, LocalDate fechaUso) {
        if (tarjeta.puedeUsarse()) {
            this.heladera = heladera;
            this.tarjeta = tarjeta;
            this.fechaUso = LocalDate.now();
            this.procesar();
        } else {
            throw new IllegalArgumentException("La tarjeta no puede ser usada. (limite de usos diarios excedido)");
        }
    }

    private void procesar() {
        this.tarjeta.agregarUso(this);
        this.heladera.extraerVianda();
    }

    public void setTarjeta(TarjetaPersVuln tarjeta) {
        this.tarjeta = tarjeta;
    }

    public TarjetaPersVuln getTarjeta() {
        return tarjeta;
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
