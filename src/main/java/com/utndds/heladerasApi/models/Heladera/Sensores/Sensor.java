package com.utndds.heladerasApi.models.Heladera.Sensores;

import com.utndds.heladerasApi.models.Heladera.Heladera;

public abstract class Sensor {
    Heladera heladera;

    Sensor(Heladera heladera) {
        this.heladera = heladera;
    }
}
