package com.utndds.heladerasApi.models.Colaboraciones;

import com.utndds.heladerasApi.models.Heladera.Heladera;

import javax.persistence.*;

@Entity
public class ObtencionHeladera extends Colaboracion {

    @ManyToOne
    @JoinColumn(name = "heladera")
    private Heladera heladera;

    // Constructor vacío para JPA
    public ObtencionHeladera() {
    }

    @Override
    protected void procesar() {
        super.procesar();
        System.out.println(
                "SE GUARDO LA OBTENCION DE HELADERA POR PARTE DE: " + this.colaborador.getPersona().getNombre());
    }

    public Heladera getHeladera() {
        return heladera;
    }

}
