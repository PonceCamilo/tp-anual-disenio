package com.utndds.heladerasApi.models.Incidentes;

import java.sql.Time;
import java.time.LocalDate;

import com.utndds.heladerasApi.models.Heladera.Heladera;

public abstract class Incidente {
    LocalDate fecha;
    Time hora;
    Heladera heladera;

    public Incidente(LocalDate fecha, Time hora, Heladera heladera) {
        this.fecha = fecha;
        this.hora = hora;
        this.heladera = heladera;

        this.desactivarHeladera();
    }

    public abstract void procesar();

    private void desactivarHeladera() {
        this.heladera.setEstado(false);
    }

}
