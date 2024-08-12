package com.utndds.heladerasApi.models.Persona;

import java.util.ArrayList;
import java.util.List;

import com.utndds.heladerasApi.models.Persona.Contacto.Contacto;

public abstract class Persona {
    String direccion;
    List<Contacto> mediosContacto = new ArrayList<>();

    public Persona(String direccion, List<Contacto> mediosContacto) {
        this.direccion = direccion;
        this.mediosContacto = mediosContacto;
    }

    public abstract String getNombre();

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Contacto> getMediosContacto() {
        return mediosContacto;
    }

    public void setMediosContacto(List<Contacto> mediosContacto) {
        this.mediosContacto = mediosContacto;
    }

    public void notificar(String message) {
        for (Contacto medio : this.mediosContacto) {
            medio.notificar(message);
        }
    }
}
