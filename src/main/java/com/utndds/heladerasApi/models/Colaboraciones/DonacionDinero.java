package com.utndds.heladerasApi.models.Colaboraciones;

import com.utndds.heladerasApi.models.Rol.Colaborador;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class DonacionDinero extends Colaboracion {

    @Column(name = "monto")
    private double monto;

    @Column(name = "frecuencia")
    private int frecuencia;

    // Constructor vacío para JPA
    public DonacionDinero() {
    }

    public DonacionDinero(Colaborador colaborador, double monto, int frecuencia) {
        super(colaborador);
        this.monto = monto;
        this.frecuencia = frecuencia;
    }

}
