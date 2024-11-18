package com.example.demo.models.Heladera;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Punto")
public class Punto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "latitud")
    private double latitud;

    @Column(name = "longitud")
    private double longitud;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "direccion")
    private String direccion;

    public Punto() {

    }

    public Punto(double latitud, double longitud, String nombre, String direccion) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.nombre = nombre;
        this.direccion = direccion;
    }

}
