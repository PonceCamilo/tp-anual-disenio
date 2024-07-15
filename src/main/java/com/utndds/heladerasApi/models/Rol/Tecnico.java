package com.utndds.heladerasApi.models.Rol;

import java.util.List;

import com.utndds.heladerasApi.models.Persona.Persona;
import com.utndds.heladerasApi.models.Rol.Contacto.Contacto;

public class Tecnico extends Rol {
    String cuil;
    String areaCobertura;

    public Tecnico(Persona persona, List<Contacto> mediosContacto, String cuil, String areaCobertura) {
        super(persona, mediosContacto);
        this.cuil = cuil;
        this.areaCobertura = areaCobertura;
    }

    public void setAreaCobertura(String areaCobertura) {
        this.areaCobertura = areaCobertura;
    }

    public String getAreaCobertura() {
        return areaCobertura;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public String getCuil() {
        return cuil;
    }

}
