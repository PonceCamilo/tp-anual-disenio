package com.utndds.heladerasApi.models.Colaboraciones;

import java.time.LocalDate;

import com.utndds.heladerasApi.models.Heladera;
import com.utndds.heladerasApi.models.PersonaVulnerable;
import com.utndds.heladerasApi.models.Colaboradores.Colaborador;

public class RegistroPersonaVulnerable extends Colaboracion {
    PersonaVulnerable personaVuln;

    public RegistroPersonaVulnerable(LocalDate fecha, Colaborador colaborador, Heladera heladera) {
        super(fecha, colaborador);
        this.heladera = heladera;
    }

    @Override
    public void realizar() {
        System.out.println("COMPLETAR EL REGISTRO PERSONA VULNERABLE");
    }

}
