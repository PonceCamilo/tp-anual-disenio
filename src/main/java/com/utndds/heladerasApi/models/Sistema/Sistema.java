package com.utndds.heladerasApi.models.Sistema;

import java.util.List;
import java.util.ArrayList;

import com.utndds.heladerasApi.models.Colaboraciones.Colaboracion;

public class Sistema {
    List<Colaboracion> colaboraciones = new ArrayList<>();

    public void agregarColaboracion(Colaboracion colaboracion) {
        this.colaboraciones.add(colaboracion);
    }

}
