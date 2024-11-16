package com.utndds.heladerasApi.DTOs;

public class OfertaDTO {

    private String rubro;
    private String nombre;
    private double cantidadPuntosNec;
    private String imagen;

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

}