package com.utndds.heladerasApi.models.Colaboraciones;

import com.utndds.heladerasApi.models.Rol.Colaborador;
import com.utndds.heladerasApi.models.Rol.PersonaVulnerable;
import com.utndds.heladerasApi.models.Tarjetas.TarjetaPersVuln.TarjetaPersVuln;

public class RegistroPersonaVulnerable extends Colaboracion {
    PersonaVulnerable personaVuln;
    TarjetaPersVuln tarjeta;

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
