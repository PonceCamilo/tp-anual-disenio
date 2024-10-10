package com.utndds.heladerasApi.models.Tarjetas;

import com.utndds.heladerasApi.models.Rol.Colaborador;

import jakarta.persistence.*;

@Entity
public class TarjetaColaborador extends Tarjeta {
    public TarjetaColaborador() {
    }

    public TarjetaColaborador(Colaborador colaborador) {
        super(colaborador);
    }

}
