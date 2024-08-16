package com.utndds.heladerasApi.models.Suscripciones.Evento;

import java.util.List;

import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Persona.Contacto.Contacto;
import com.utndds.heladerasApi.models.Rol.Colaborador;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("POCAS_VIANDAS")
public class PocasViandas extends Evento {

    @Column(name = "cantidad_minima")
    private int cantidadMinima;

    public PocasViandas() {
    }

    public PocasViandas(Colaborador colaborador, List<Contacto> mediosDeseados, int cantidadMinima) {
        super(colaborador, mediosDeseados);
        this.cantidadMinima = cantidadMinima;
    }

    public void verificarEvento(Heladera heladera) {
        if (heladera.cantViandas() <= this.cantidadMinima) {
            this.notificarEvento(heladera);
        }
    };

    protected void notificarEvento(Heladera heladera) {
        List<Contacto> contactos = this.colaborador.getPersona().getMediosContacto();
        for (Contacto contacto : contactos) {
            if (this.mediosDeseados.contains(contacto)) {
                contacto.notificar("SE notifico a " + this.colaborador.getPersona().getNombre()
                        + " que hay pocas viandas en la heladera: " + heladera.getPunto().getDireccion());
            }
        }

    };
}
