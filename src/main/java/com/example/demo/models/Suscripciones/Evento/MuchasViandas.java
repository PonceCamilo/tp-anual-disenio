package com.example.demo.models.Suscripciones.Evento;

import java.util.List;

import com.example.demo.models.Heladera.Heladera;
import com.example.demo.models.Persona.Contacto.Contacto;

import jakarta.persistence.*;
import lombok.Setter;

@Entity
@DiscriminatorValue("MUCHAS_VIANDAS")
public class MuchasViandas extends Evento {

    @Setter
    @Column(name = "cantidad_maxima")
    private int cantidadMaxima;

    public MuchasViandas() {
    }

    public MuchasViandas(List<Contacto> mediosDeseados, int cantidadMaxima) {
        super(mediosDeseados);
        this.cantidadMaxima = cantidadMaxima;
    }

    public void verificarEvento(Heladera heladera) {
        if (heladera.cantViandas() >= this.cantidadMaxima) {
            this.notificarEvento(heladera);
        }
    };

    protected void notificarEvento(Heladera heladera) {
        List<Contacto> contactos = this.suscripcion.getColaborador().getPersona().getMediosContacto();
        for (Contacto contacto : contactos) {
            if (this.mediosDeseados.contains(contacto)) {
                contacto.notificar("Se notifico a " + this.suscripcion.getColaborador().getPersona().getNombre()
                        + " que hay muchas viandas en la heladera: " + heladera.getPunto().getDireccion());
            }
        }

    };
}
