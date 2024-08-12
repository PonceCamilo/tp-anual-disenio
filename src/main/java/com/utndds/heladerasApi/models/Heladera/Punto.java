package com.utndds.heladerasApi.models.Heladera;

public class Punto {
    private double latitud;
    private double longitud;
    private String nombre;
    private String direccion;

    public Punto() {
        // Constructor vacío requerido por Spring para deserialización JSON
    }

    public Punto(double latitud, double longitud, String nombre, String direccion) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

}
