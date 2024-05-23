package com.utndds.heladerasApi.models.Colaboraciones;

public class Oferta {
    String rubro;
    String nombre;
    double cantidadPuntosNec;
    String imagen;

    public Oferta(String rubro, String nombre, double cantidadPuntosNec, String imagen) {
        this.rubro = rubro;
        this.nombre = nombre;
        this.cantidadPuntosNec = cantidadPuntosNec;
        this.imagen = imagen;
    }

    public Oferta(String rubro, String nombre, double cantidadPuntosNec) {
        this.rubro = rubro;
        this.nombre = nombre;
        this.cantidadPuntosNec = cantidadPuntosNec;
        this.imagen = null;
    }

    public void realizar() {
        System.out.println("SE REALIZO LA OFERTA");
    }
}
