package com.utndds.heladerasApi.models.Heladera.Sensores;

import com.utndds.heladerasApi.models.Heladera.Heladera;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "heladera") // Asegúrate de que el nombre sea coherente con las tablas
    protected Heladera heladera;

    // Constructor vacío para JPA
    protected Sensor() {
    }

    Sensor(Heladera heladera) {
        this.heladera = heladera;
    }
}
