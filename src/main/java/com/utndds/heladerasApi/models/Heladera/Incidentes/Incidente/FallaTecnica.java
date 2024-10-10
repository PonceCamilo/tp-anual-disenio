package com.utndds.heladerasApi.models.Heladera.Incidentes.Incidente;

import com.utndds.heladerasApi.models.Heladera.Heladera;

import jakarta.persistence.*;

@Entity
@Table(name = "falla_tecnica")
public class FallaTecnica extends Incidente {

    // Constructor vacío para JPA
    public FallaTecnica() {
    }

    public FallaTecnica(Heladera heladera) {
        super(heladera);
        this.procesar();
    }

    @Override
    public void procesar() {
        super.procesar();
        this.heladera.verificarSuscripciones();
    };

}
