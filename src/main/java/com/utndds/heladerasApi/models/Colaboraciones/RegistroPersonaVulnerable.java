package com.utndds.heladerasApi.models.Colaboraciones;

import java.time.LocalDate;

import com.utndds.heladerasApi.models.Persona.Colaboradores.Colaborador;
import com.utndds.heladerasApi.models.Persona.personaVulnerable.PersonaVulnerable;

public class RegistroPersonaVulnerable extends Colaboracion {
    PersonaVulnerable personaVuln;

    public RegistroPersonaVulnerable(LocalDate fecha, Colaborador colaborador, PersonaVulnerable personaVuln) {
        super(fecha, colaborador);
        this.personaVuln = personaVuln;
    }

    @Override
    public void realizar() {
        System.out.println("SE REALIZO EL REGISTRO DE PERSONA VULNERABLE");
    }

    @Override
    public double puntosGanados() {
        System.out.println("CORREGIR PUNTOS GANADOS");
        return this.obtenerCoeficiente();
    }

    @Override
    protected double obtenerCoeficiente() {
        return 23;
    }

}
