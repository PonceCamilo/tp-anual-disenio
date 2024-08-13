package com.utndds.heladerasApi.models.Heladera.Incidentes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.utndds.heladerasApi.models.Heladera.Heladera;

public abstract class Incidente {
    LocalDateTime fechaHora;
    Heladera heladera;
    List<VisitaTecnico> visitas = new ArrayList<>();

    public Incidente(Heladera heladera) {
        this.fechaHora = LocalDateTime.now();
        this.heladera = heladera;

        this.procesar();
    }

    public void procesar() {
        this.desactivarHeladera();
    };

    private void desactivarHeladera() {
        this.heladera.setFuncionando(false);
    }

    public void agregarVisita(VisitaTecnico visita) {
        this.visitas.add(visita);
    }

    public Heladera getHeladera() {
        return heladera;
    }
}
