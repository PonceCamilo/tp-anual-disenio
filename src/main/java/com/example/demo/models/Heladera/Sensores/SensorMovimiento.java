package com.example.demo.models.Heladera.Sensores;

import com.example.demo.models.Heladera.Heladera;

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