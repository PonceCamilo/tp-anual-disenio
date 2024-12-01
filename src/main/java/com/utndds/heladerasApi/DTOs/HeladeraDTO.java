package com.utndds.heladerasApi.DTOs;

public class HeladeraDTO {
    private int capacidad;
    private Double temperaturaMaxima;
    private Double temperaturaMinima;
    private Long id;
    private String nombrePunto;

    public HeladeraDTO() {
    }

    public HeladeraDTO(Long id, String nombrePunto) {
        this.id = id;
        this.nombrePunto = nombrePunto;
    }

    public String getNombrePunto() {
        return nombrePunto;
    }

    public void setNombrePunto(String nombrePunto) {
        this.nombrePunto = nombrePunto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public Double getTemperaturaMaxima() {
        return temperaturaMaxima;
    }

    public void setTemperaturaMaxima(Double temperaturaMaxima) {
        this.temperaturaMaxima = temperaturaMaxima;
    }

    public Double getTemperaturaMinima() {
        return temperaturaMinima;
    }

    public void setTemperaturaMinima(Double temperaturaMinima) {
        this.temperaturaMinima = temperaturaMinima;
    }
}