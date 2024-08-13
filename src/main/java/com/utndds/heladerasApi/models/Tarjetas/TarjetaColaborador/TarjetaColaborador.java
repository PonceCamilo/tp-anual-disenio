package com.utndds.heladerasApi.models.Tarjetas.TarjetaColaborador;

import java.util.ArrayList;
import java.util.List;

import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Heladera.SolicitudApertura;
import com.utndds.heladerasApi.models.Rol.Colaborador;
import com.utndds.heladerasApi.models.Sistema.Sistema;
import com.utndds.heladerasApi.models.Tarjetas.Tarjeta;

public class TarjetaColaborador extends Tarjeta {
    Colaborador colaborador;
    List<Apertura> aperturas = new ArrayList<>();

    public TarjetaColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;

    }

    public boolean puedeUsarse(Heladera heladera) {
        List<SolicitudApertura> solicitudes = Sistema.getInstance().getSolicitudes();
        return solicitudes.stream()
                .filter(solicitud -> solicitud.getColaborador() == this.colaborador)
                .anyMatch(SolicitudApertura::getEstado);
    }

    protected void usar(Heladera heladera) {
        new Apertura(heladera, this);

    }

    public void agregarApertura(Apertura apertura) {
        this.aperturas.add(apertura);
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

}
