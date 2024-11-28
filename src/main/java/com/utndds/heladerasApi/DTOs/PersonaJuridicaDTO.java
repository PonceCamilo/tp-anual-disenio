package com.utndds.heladerasApi.DTOs;
import java.util.List;

import com.utndds.heladerasApi.models.Persona.Contacto.Contacto;
public class PersonaJuridicaDTO {
    
    private String razonSocial;
    private String tipo;
    private String rubro;
    private String direccion;
    private List<Contacto> mediosContacto;

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
    }

    public String getRubro() {
        return rubro;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setMediosContacto(List<Contacto> mediosContacto) {
        this.mediosContacto = mediosContacto;
    }

    public List<Contacto> getMediosContacto() {
        return mediosContacto;
    }

    public PersonaJuridicaDTO(String direccion, List<Contacto> mediosContacto, String razonSocial, String tipo, String rubro) {
        this.razonSocial = razonSocial;
        this.tipo = tipo;
        this.rubro = rubro;
        this.direccion = direccion;
        this.mediosContacto = mediosContacto;
    }

}
