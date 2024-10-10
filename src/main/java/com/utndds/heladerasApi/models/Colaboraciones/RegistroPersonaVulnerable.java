package com.utndds.heladerasApi.models.Colaboraciones;

import com.utndds.heladerasApi.models.Rol.Colaborador;
import com.utndds.heladerasApi.models.Rol.PersonaVulnerable;
import com.utndds.heladerasApi.models.Tarjetas.TarjetaPersVuln;

import jakarta.persistence.*;

@Entity
public class RegistroPersonaVulnerable extends Colaboracion {

    @ManyToOne
    @JoinColumn(name = "persona_vulnerable")
    private PersonaVulnerable personaVuln;

    @ManyToOne
    @JoinColumn(name = "tarjeta_pers_vuln")
    private TarjetaPersVuln tarjeta;

    // Constructor vacío para JPA
    public RegistroPersonaVulnerable() {
    }

    public RegistroPersonaVulnerable(Colaborador colaborador, PersonaVulnerable personaVuln, TarjetaPersVuln tarjeta) {
        super(colaborador);
        this.personaVuln = personaVuln;
        this.tarjeta = tarjeta;
    }

}
