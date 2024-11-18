package com.example.demo.models.Suscripciones.Evento;

import java.util.List;

import com.example.demo.models.Heladera.Heladera;
import com.example.demo.models.Persona.Contacto.Contacto;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("DESPERFECTO")
public class Desperfecto extends Evento {

    public Desperfecto() {
    }

    public Desperfecto(List<Contacto> mediosDeseados) {
        super(mediosDeseados);
    }

    public void verificarEvento(Heladera heladera) {
        if (!heladera.estaFuncionando()) {
            this.notificarEvento(heladera);
        }
    };

    protected void notificarEvento(Heladera heladera) {
        List<Contacto> contactos = this.suscripcion.getColaborador().getPersona().getMediosContacto();
        for (Contacto contacto : contactos) {
            if (this.mediosDeseados.contains(contacto)) {
                contacto.notificar("Se notifico a " + this.suscripcion.getColaborador().getPersona().getNombre()
                        + " que hay un desperfecto en la heladera: " + heladera.getPunto().getDireccion());
            }
        }

    };
}
