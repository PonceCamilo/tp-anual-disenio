package com.utndds.heladerasApi.models.Colaboraciones;

import com.utndds.heladerasApi.models.Rol.Colaborador;
import com.utndds.heladerasApi.models.Rol.PersonaVulnerable;
import com.utndds.heladerasApi.models.Tarjetas.TarjetaPersVuln.TarjetaPersVuln;

import javax.persistence.*;

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
        this.procesar();
    }

    @Override
    protected void procesar() {
        super.procesar();
        this.personaVuln.setTarjeta(this.tarjeta);
        this.tarjeta.setPersVul(this.personaVuln);
        System.out.println("SE GUARDO EL REGISTRO DE PERSONA VULNERABLE POR PARTE DE: "
                + this.colaborador.getPersona().getNombre());
    }

}
