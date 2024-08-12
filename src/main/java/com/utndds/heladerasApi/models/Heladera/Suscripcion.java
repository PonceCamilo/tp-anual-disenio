package com.utndds.heladerasApi.models.Heladera;

import com.utndds.heladerasApi.models.Rol.Colaborador;

public class Suscripcion {
    Heladera heladera;
    Colaborador colaborador;

    // Constructor corregido
    public Suscripcion(Heladera heladera, Colaborador colaborador) {
        this.heladera = heladera;
        this.colaborador = colaborador;
    }

}
