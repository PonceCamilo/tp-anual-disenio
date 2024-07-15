package com.utndds.heladerasApi.models.Rol;

import java.time.LocalDate;
import java.util.List;

import com.utndds.heladerasApi.models.Persona.Persona;
import com.utndds.heladerasApi.models.Rol.Contacto.Contacto;

public class PersonaVulnerable extends Rol {
    LocalDate fechaRegistro;
    boolean situacionCalle;
    String domicilio;
    int cantMenoresAcargo;

    public PersonaVulnerable(Persona persona, List<Contacto> mediosContacto, LocalDate fechaRegistro,
            boolean situacionCalle, String domicilio, int cantMenoresAcargo) {
        super(persona, mediosContacto);
        this.fechaRegistro = fechaRegistro;
        this.situacionCalle = situacionCalle;
        this.domicilio = domicilio;
        this.cantMenoresAcargo = cantMenoresAcargo;
    }

    public int extraccionesDiariasPermitidas() {
        return 4 + 2 * this.cantMenoresAcargo;
    }

}