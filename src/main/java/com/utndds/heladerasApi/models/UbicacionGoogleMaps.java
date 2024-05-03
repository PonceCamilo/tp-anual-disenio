package com.utndds.heladerasApi.models;

public class UbicacionGoogleMaps {
    private String nombre;
    private double latitud;
    private double longitud;
    private String provincia;

    public UbicacionGoogleMaps() {
        // Constructor vacío requerido por Spring para deserialización JSON
    }

    public UbicacionGoogleMaps(String nombre, double latitud, double longitud, String provincia) {
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
        this.provincia = provincia;
    }

    // Getters y setters para nombre, latitud, longitud y provincia

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
}

