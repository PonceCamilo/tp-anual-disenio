package com.utndds.heladerasApi.models.Colaboradores;

import java.util.List;

import com.utndds.heladerasApi.models.Colaboraciones.Colaboracion;

public class PersonaJuridica extends Colaborador {

    String razonSocial;
    String tipo;
    String rubro;

    public PersonaJuridica(List<Contacto> mediosDeContacto, String direccion, List<Colaboracion> colaboraciones,
            String razonSocial, String tipo, String rubro) {
        super(mediosDeContacto, direccion, colaboraciones);
        this.razonSocial = razonSocial;
        this.tipo = tipo;
        this.rubro = rubro;
    }
}
