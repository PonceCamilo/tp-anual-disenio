package com.utndds.heladerasApi.models.Heladera.Sensores;

import com.utndds.heladerasApi.models.Heladera.Heladera;
import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // Define la estrategia de herencia
public abstract class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "heladera") // Nombre de la columna de la FK en la tabla Sensor
    protected Heladera heladera;

    // Constructor vacío para JPA
    protected Sensor() {
    }

    Sensor(Heladera heladera) {
        this.heladera = heladera;
    }
}
