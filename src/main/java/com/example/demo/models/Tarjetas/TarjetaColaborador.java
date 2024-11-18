package com.example.demo.models.Tarjetas;

import com.example.demo.models.Rol.Colaborador;

import jakarta.persistence.*;

@Entity
public class TarjetaColaborador extends Tarjeta {
    public TarjetaColaborador() {
    }

    public TarjetaColaborador(Colaborador colaborador) {
        super(colaborador);
    }

}
