package com.utndds.heladerasApi.models.Colaboraciones;

import com.utndds.heladerasApi.models.Rol.Colaborador;

public class Oferta extends Colaboracion {
    String rubro;
    String nombre;
    double cantidadPuntosNec;
    String imagen;

    public Oferta(Colaborador colaborador, String rubro, String nombre, double cantidadPuntosNec,
            String imagen) {
        super(colaborador);
        this.colaborador = colaborador;
        this.rubro = rubro;
        this.nombre = nombre;
        this.cantidadPuntosNec = cantidadPuntosNec;
        this.imagen = imagen;
    }

    protected void procesar() {
        super.procesar();
        System.out.println("SE GUARDO LA OFERTA POR PARTE DE: " + this.colaborador.getPersona().getNombre());
    }

    public void canjear(Colaborador colaborador) {
        if ((this.verificarPuntosRequeridos(colaborador)) == true) {
            System.out.println(
                    "el colaborador " + colaborador.getPersona().getNombre() + "canjeo la oferta " + this.nombre);

        } else {
            System.out.println("el colaborador " + colaborador.getPersona().getNombre()
                    + "no tiene puntos necesarios para canjear la oferta " + this.nombre);
        }

    }

    private boolean verificarPuntosRequeridos(Colaborador colaborador) {
        return colaborador.puntos() >= this.puntosGanados();
    }

}
