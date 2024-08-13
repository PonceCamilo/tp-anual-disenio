package com.utndds.heladerasApi.models.Heladera.Suscripciones;

import java.util.ArrayList;
import java.util.List;

import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Heladera.Suscripciones.Evento.Evento;
import com.utndds.heladerasApi.models.Rol.Colaborador;

public class Suscripcion {
    Heladera heladera;
    Colaborador colaborador;
    List<Evento> notificacionesDeseadas = new ArrayList<>();

    // Constructor corregido
    public Suscripcion(Heladera heladera, Colaborador colaborador) {
        this.heladera = heladera;
        this.colaborador = colaborador;

        this.procesar();
    }

    private void procesar() {
        this.heladera.agregarSuscripcion(this);
        this.colaborador.agregarSuscripcion(this);
    };

    public void verificarNotificaciones() {
        for (Evento evento : notificacionesDeseadas) {
            evento.verificarEvento(this.heladera);
        }
    }

}
