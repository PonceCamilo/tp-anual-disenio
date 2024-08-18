package com.utndds.heladerasApi.services.broker;

import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Heladera.Incidentes.Alerta;

public class BrokerAlerta {

    // Método que recibe una alerta desde el sistema externo
    public void recibirAlerta(Heladera heladera, String tipo) {
        if (heladera != null) {
            new Alerta(heladera, tipo);
        } else {
            System.out.println("Heladera no encontrada");
        }
    }
}
