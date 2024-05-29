package com.utndds.heladerasApi.models.Persona.Colaboradores;

import java.util.List;

import com.utndds.heladerasApi.models.Colaboraciones.Colaboracion;
import com.utndds.heladerasApi.models.Persona.Contacto.Contacto;

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

    public void alta() {
        System.out.println("se dio de alta la persona juridica");
    }

    public void baja() {
        System.out.println("se dio de baja la persona juridica");
    }

    public void modificar() {
        System.out.println("se modifico la persona juridica");
    }
}
