package com.utndds.heladerasApi.models.Heladera.Incidentes;

import com.utndds.heladerasApi.models.Heladera.Heladera;
import jakarta.persistence.*;

@Entity
@Table(name = "alerta")
public class Alerta extends Incidente {

    @Column(name = "tipo")
    private String tipo;

    // Constructor vacío para JPA
    public Alerta() {
    }

    public Alerta(Heladera heladera, String tipo) {
        super(heladera);
        this.tipo = tipo;
        this.procesar();
    }

    public void procesar() {
        super.procesar();
    };

}
