package com.utndds.heladerasApi.models.Observer;

import com.utndds.heladerasApi.models.Heladera.Heladera;

public interface ObservadorSuscripcion extends Observer {
    public abstract void verificarNotificaciones(Heladera heladera);
}
