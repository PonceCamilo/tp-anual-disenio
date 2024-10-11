package com.utndds.heladerasApi.models.Heladera.Sensores;

import com.utndds.heladerasApi.models.Heladera.Heladera;

import jakarta.persistence.*;

@Entity
@Table(name = "sensor_movimiento")
public class SensorMovimiento extends Sensor {
    // Constructor vacío para JPA
    public SensorMovimiento() {
    }

    public SensorMovimiento(Heladera heladera) {
        super(heladera);
    }
}