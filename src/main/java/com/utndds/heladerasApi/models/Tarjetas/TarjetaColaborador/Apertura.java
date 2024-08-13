package com.utndds.heladerasApi.models.Tarjetas.TarjetaColaborador;

import java.time.LocalDate;

import com.utndds.heladerasApi.models.Heladera.Heladera;

public class Apertura {
    Heladera heladera;
    TarjetaColaborador tarjetaColaborador;
    LocalDate fecha;

    public Apertura(Heladera heladera, TarjetaColaborador tarjetaColaborador) {
        this.heladera = heladera;
        this.tarjetaColaborador = tarjetaColaborador;
        this.fecha = LocalDate.now();
        this.procesar();

    }

    private void procesar() {
        this.tarjetaColaborador.agregarApertura(this);
    }

}
