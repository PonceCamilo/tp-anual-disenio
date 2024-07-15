package com.utndds.heladerasApi.models.Incidentes;

import java.sql.Time;
import java.time.LocalDate;

import com.utndds.heladerasApi.models.Heladera.Heladera;

public class Alerta extends Incidente {

    public Alerta(LocalDate fecha, Time hora, Heladera heladera) {
        super(fecha, hora, heladera);
    }

    @Override
    public void procesar() {

    };
}
