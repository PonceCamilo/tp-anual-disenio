package com.utndds.heladerasApi.models.Rol.Tecnico;

import jakarta.persistence.*;

@Entity
@Table(name = "area_cobertura") // Nombre de la tabla en la base de datos
public class AreaCobertura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") // Columna de ID
    private Long id;

    @Column(name = "latitud_centro") // Columna para latitud del centro
    private double latitudCentro;

    @Column(name = "longitud_centro") // Columna para longitud del centro
    private double longitudCentro;

    @Column(name = "radio") // Columna para el radio
    private double radio;

    // Constructor vacío para JPA
    public AreaCobertura() {
    }

    public AreaCobertura(double latitudCentro, double longitudCentro, double radio) {
        this.latitudCentro = latitudCentro;
        this.longitudCentro = longitudCentro;
        this.radio = radio;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getLatitudCentro() {
        return latitudCentro;
    }

    public void setLatitudCentro(double latitudCentro) {
        this.latitudCentro = latitudCentro;
    }

    public double getLongitudCentro() {
        return longitudCentro;
    }

    public void setLongitudCentro(double longitudCentro) {
        this.longitudCentro = longitudCentro;
    }

    public double getRadio() {
        return radio;
    }

    public void setRadio(double radio) {
        this.radio = radio;
    }
}