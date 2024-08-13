package com.utndds.heladerasApi.models.Tarjetas;

import com.utndds.heladerasApi.models.Heladera.Heladera;

public abstract class Tarjeta {
    public abstract boolean puedeUsarse(Heladera heladera);

    protected abstract void usar(Heladera heladera);
}
