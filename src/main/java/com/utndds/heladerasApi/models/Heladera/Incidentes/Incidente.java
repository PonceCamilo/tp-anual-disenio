package com.utndds.heladerasApi.models.Heladera.Incidentes;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import com.utndds.heladerasApi.models.Heladera.Heladera;

public abstract class Incidente {
    ZonedDateTime fechaHora;
    Heladera heladera;
    List<VisitaTecnico> visitas = new ArrayList<>();

    public Incidente(Heladera heladera) {
        this.fechaHora = ZonedDateTime.now();
        this.heladera = heladera;

        this.procesar();
    }

    public void procesar() {
        this.desactivarHeladera();
    };

    private void desactivarHeladera() {
        this.heladera.setFuncionando(false);
    }

    public Heladera getHeladera() {
        return heladera;
    }
}
