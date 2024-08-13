package com.utndds.heladerasApi.models.Heladera.Suscripciones.Evento;

import java.util.ArrayList;
import java.util.List;

import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Rol.Colaborador;
import com.utndds.heladerasApi.models.Persona.Contacto.Contacto;

public abstract class Evento {
    Colaborador colaborador;
    List<Contacto> mediosDeseados = new ArrayList<>();

    public Evento(Colaborador colaborador, List<Contacto> mediosDeseados) {
        this.colaborador = colaborador;
        this.mediosDeseados = mediosDeseados;
    }

    public abstract void verificarEvento(Heladera heladera);

    protected abstract void notificarEvento(Heladera heladera);
}
