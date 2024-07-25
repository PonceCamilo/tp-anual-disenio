package com.utndds.heladerasApi.models.Persona;

import java.util.List;

import com.utndds.heladerasApi.models.Persona.Contacto.Contacto;

public class PersonaJuridica extends Persona {
    String razonSocial;
    String tipo;
    String rubro;

    public PersonaJuridica(String direccion, List<Contacto> mediosContacto, String razonSocial, String tipo,
            String rubro) {
        super(direccion, mediosContacto);
        this.razonSocial = razonSocial;
        this.tipo = tipo;
        this.rubro = rubro;
    }

    @Override
    public String getNombre() {
        return this.razonSocial;
    };

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRubro() {
        return rubro;
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
