package com.utndds.heladerasApi.models.Suscripciones.Evento;

import java.util.List;

import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Persona.Contacto.Contacto;
import com.utndds.heladerasApi.models.Rol.Colaborador;
import javax.persistence.*;

@Entity
@DiscriminatorValue("DESPERFECTO")
public class Desperfecto extends Evento {

    public Desperfecto() {
    }

    public Desperfecto(Colaborador colaborador, List<Contacto> mediosDeseados) {
        super(colaborador, mediosDeseados);
    }

    public void verificarEvento(Heladera heladera) {
        if (!heladera.estaFuncionando()) {
            this.notificarEvento(heladera);
        }
    };

    protected void notificarEvento(Heladera heladera) {
        List<Contacto> contactos = this.colaborador.getPersona().getMediosContacto();
        for (Contacto contacto : contactos) {
            if (this.mediosDeseados.contains(contacto)) {
                contacto.notificar("Se notifico a " + this.colaborador.getPersona().getNombre()
                        + " que hay un desperfecto en la heladera: " + heladera.getPunto().getDireccion());
            }
        }

    };
}
