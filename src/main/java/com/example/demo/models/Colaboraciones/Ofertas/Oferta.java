package com.example.demo.models.Colaboraciones.Ofertas;

import com.example.demo.models.Colaboraciones.Colaboracion;
import com.example.demo.models.Rol.Colaborador;

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

    // Constructor con los datos necesarios
    public Oferta(Colaborador colaborador, String rubro, String nombre, double cantidadPuntosNec, String imagen) {
        super(colaborador); // Llama al constructor de la clase base 'Colaboracion'
        this.rubro = rubro;
        this.nombre = nombre;
        this.cantidadPuntosNec = cantidadPuntosNec;
        this.imagen = imagen;
    }
}