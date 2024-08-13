package com.utndds.heladerasApi.models.Rol;

import java.time.LocalDate;

import com.utndds.heladerasApi.models.Persona.Persona;
import com.utndds.heladerasApi.models.Tarjetas.TarjetaPersVuln.TarjetaPersVuln;

public class PersonaVulnerable extends Rol {
    LocalDate fechaRegistro;
    boolean situacionCalle;
    int cantMenoresAcargo;
    TarjetaPersVuln tarjeta;

    public PersonaVulnerable(Persona persona, boolean situacionCalle, int cantMenoresAcargo) {
        super(persona);
        this.fechaRegistro = LocalDate.now();
        this.situacionCalle = situacionCalle;
        this.cantMenoresAcargo = cantMenoresAcargo;
    }

    public int extraccionesDiariasPermitidas() {
        return 4 + 2 * this.cantMenoresAcargo;
    }

    public void setTarjeta(TarjetaPersVuln tarjeta) {
        this.tarjeta = tarjeta;
    }

}