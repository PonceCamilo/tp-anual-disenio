package com.utndds.heladerasApi.models.Rol.Tecnico;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

}