package com.utndds.heladerasApi.models.Rol;

import com.utndds.heladerasApi.models.Persona.Persona;
import com.utndds.heladerasApi.models.Persona.Contacto.Contacto;

public abstract class Rol {
    Persona persona;

    public Rol(Persona persona) {
        this.persona = persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Persona getPersona() {
        return persona;
    }

    public void notificar(String mensaje) {
        for (Contacto medio : persona.getMediosContacto()) {
            medio.notificar(mensaje);
        }
    }
}
