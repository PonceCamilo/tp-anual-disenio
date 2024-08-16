package com.utndds.heladerasApi.models.Colaboraciones;

import com.utndds.heladerasApi.models.Rol.Colaborador;

import jakarta.persistence.*;

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

    @Override
    protected void procesar() {
        super.procesar();
        System.out.println(
                "SE GUARDO LA DONACION DE DINERO POR PARTE DE: " + this.colaborador.getPersona().getNombre());
    }

    public double getMonto() {
        return monto;
    }
}
