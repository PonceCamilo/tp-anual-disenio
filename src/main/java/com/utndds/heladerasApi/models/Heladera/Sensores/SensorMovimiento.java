package com.utndds.heladerasApi.models.Heladera.Sensores;

import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Heladera.Incidentes.Alerta;

public class SensorMovimiento extends Sensor {

    public SensorMovimiento(Heladera heladera) {
        super(heladera);
    }

    public void hayFraude() {
        System.out.println("se detecto un intento de robo en la heladera: " + this.heladera.getPunto().getNombre());
        new Alerta(this.heladera, "Fraude");
    }
}