package com.utndds.heladerasApi.DTOs;

public class OfertaDTO {

    private String rubro;
    private String nombre;
    private double cantidadPuntosNec;
    private String imagen;
    private String colaboradorUUID; // El ID de la empresa que está ofreciendo la oferta

    // Getters y Setters
    public String getRubro() {
        return rubro;
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCantidadPuntosNec() {
        return cantidadPuntosNec;
    }

    public void setCantidadPuntosNec(double cantidadPuntosNec) {
        this.cantidadPuntosNec = cantidadPuntosNec;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getColaboradorUUID() {
        return colaboradorUUID;
    }

    public void setColaboradorUUID(String colaboradorUUID) {
        this.colaboradorUUID = colaboradorUUID;
    }
}