package com.utndds.heladerasApi.models.Colaboraciones.Ofertas;

import com.utndds.heladerasApi.models.Colaboraciones.Colaboracion;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
}
