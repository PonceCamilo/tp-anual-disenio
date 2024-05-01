package com.utndds.heladerasApi.models.Colaboradores;

public class PersonaJuridica extends Colaborador{

    String razonSocial;
    String tipo;
    String rubro;

    public PersonaJuridica(String direccion, String razonSocial, String tipo, String rubro) {
        super(direccion);
        this.razonSocial = razonSocial;
        this.tipo = tipo;
        this.rubro = rubro;
    }
}
