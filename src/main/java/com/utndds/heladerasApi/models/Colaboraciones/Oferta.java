package com.utndds.heladerasApi.models.Colaboraciones;

import java.time.LocalDate;

import com.utndds.heladerasApi.models.Rol.Colaborador;

public class Oferta extends Colaboracion {
    String rubro;
    String nombre;
    double cantidadPuntosNec;
    String imagen;

    public Oferta(LocalDate fecha, Colaborador colaborador, String rubro, String nombre, double cantidadPuntosNec,
            String imagen) {
        super(fecha, colaborador);
        this.fecha = fecha;
        this.colaborador = colaborador;
        this.rubro = rubro;
        this.nombre = nombre;
        this.cantidadPuntosNec = cantidadPuntosNec;
        this.imagen = imagen;
    }

    public void procesar() {
        System.out.println("SE REALIZO LA OFERTA");
    }

}
