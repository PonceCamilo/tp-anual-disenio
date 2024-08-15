package com.utndds.heladerasApi.models.Colaboraciones;

import com.utndds.heladerasApi.models.Rol.Colaborador;

import javax.persistence.*;

@Entity
public class Oferta extends Colaboracion {

    @Column(name = "rubro")
    private String rubro;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "cantidad_puntos_nec")
    private double cantidadPuntosNec;

    @Column(name = "imagen")
    private String imagen;

    // Constructor vacío para JPA
    public Oferta() {
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
