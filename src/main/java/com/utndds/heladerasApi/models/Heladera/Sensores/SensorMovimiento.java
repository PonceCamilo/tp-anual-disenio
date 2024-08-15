package com.utndds.heladerasApi.models.Heladera.Sensores;

import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Heladera.Incidentes.Alerta;

import javax.persistence.*;

@Entity
@Table(name = "sensor_movimiento")
public class SensorMovimiento extends Sensor {

    // Constructor vacío para JPA
    protected SensorMovimiento() {
    }

    public SensorMovimiento(Heladera heladera) {
        super(heladera);
    }

    public void hayFraude() {
        System.out
                .println("Se detectó un intento de robo en la heladera: " + this.heladera.getPunto().getNombre());
        new Alerta(this.heladera, "Fraude");
    }

}