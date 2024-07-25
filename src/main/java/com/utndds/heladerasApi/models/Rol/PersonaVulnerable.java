package com.utndds.heladerasApi.models.Rol;

import java.time.LocalDate;

import com.utndds.heladerasApi.models.Persona.Persona;

public class PersonaVulnerable extends Rol {
    LocalDate fechaRegistro;
    boolean situacionCalle;
    int cantMenoresAcargo;

    public PersonaVulnerable(Persona persona, LocalDate fechaRegistro,
            boolean situacionCalle, int cantMenoresAcargo) {
        super(persona);
        this.fechaRegistro = fechaRegistro;
        this.situacionCalle = situacionCalle;
        this.cantMenoresAcargo = cantMenoresAcargo;
    }

    public int extraccionesDiariasPermitidas() {
        return 4 + 2 * this.cantMenoresAcargo;
    }

}