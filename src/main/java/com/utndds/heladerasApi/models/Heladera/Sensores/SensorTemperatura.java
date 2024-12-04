package com.utndds.heladerasApi.models.Heladera.Sensores;

import com.utndds.heladerasApi.models.Heladera.Heladera;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sensor_temperatura")
public class SensorTemperatura extends Sensor {
    @Getter
    @Setter
    @Column(name = "temperatura")
    private Double temperatura;

    // Constructor vacío para JPA
    public SensorTemperatura() {
    }

    public SensorTemperatura(Heladera heladera) {
        super(heladera);
    }

    public void actualizarTemperatura(double temperatura) {
        this.temperatura = temperatura;
        this.heladera.actualizarTemperatura(this.temperatura);
    }
}
