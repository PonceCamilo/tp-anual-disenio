package com.utndds.heladerasApi.models.Rol;

import java.util.ArrayList;
import java.util.List;

import com.utndds.heladerasApi.models.Persona.Persona;
import com.utndds.heladerasApi.models.Rol.Contacto.Contacto;

public abstract class Rol {
    Persona persona;
    List<Contacto> mediosContacto = new ArrayList<>();

    public Rol(Persona persona, List<Contacto> mediosContacto) {
        this.persona = persona;
        this.mediosContacto = mediosContacto;
    }

    public void setMediosContacto(List<Contacto> mediosContacto) {
        this.mediosContacto = mediosContacto;
    }

    public List<Contacto> getMediosContacto() {
        return mediosContacto;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Persona getPersona() {
        return persona;
    }

    public void notificar(String mensaje) {
        for (Contacto medio : mediosContacto) {
            medio.notificar(mensaje);
        }
    }
}
