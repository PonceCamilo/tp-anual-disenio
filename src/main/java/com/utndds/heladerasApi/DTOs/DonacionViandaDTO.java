package com.utndds.heladerasApi.DTOs;

import java.time.LocalDate;

public class DonacionViandaDTO {

    private String comida;
    private LocalDate fechaCaducidad;
    private Long heladeraId;
    private double calorias;
    private double peso;

    // Constructor vacío
    public DonacionViandaDTO() {
    }

    // Getters y Setters
    public String getComida() {
        return comida;
    }

    public void setComida(String comida) {
        this.comida = comida;
    }

    public LocalDate getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(LocalDate fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public Long getHeladeraId() {
        return heladeraId;
    }

    public void setHeladeraId(Long heladeraId) {
        this.heladeraId = heladeraId;
    }

    public double getCalorias() {
        return calorias;
    }

    public void setCalorias(double calorias) {
        this.calorias = calorias;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

}