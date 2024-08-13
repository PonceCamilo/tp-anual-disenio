package com.utndds.heladerasApi.models.Heladera.Suscripciones.Evento;

import java.util.List;

import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Persona.Contacto.Contacto;
import com.utndds.heladerasApi.models.Rol.Colaborador;

public class MuchasViandas extends Evento {
    int cantidadMaxima;

    public MuchasViandas(Colaborador colaborador, List<Contacto> mediosDeseados, int cantidadMaxima) {
        super(colaborador, mediosDeseados);
        this.cantidadMaxima = cantidadMaxima;
    }

    public void verificarEvento(Heladera heladera) {
        if (heladera.cantViandas() >= this.cantidadMaxima) {
            this.notificarEvento(heladera);
        }
    };

    protected void notificarEvento(Heladera heladera) {
        List<Contacto> contactos = this.colaborador.getPersona().getMediosContacto();
        for (Contacto contacto : contactos) {
            if (this.mediosDeseados.contains(contacto)) {
                contacto.notificar("Se notifico a " + this.colaborador.getPersona().getNombre()
                        + " que hay muchas viandas en la heladera: " + heladera.getPunto().getDireccion());
            }
        }

    };
}
