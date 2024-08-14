package com.utndds.heladerasApi.models.Heladera;

public class Ubicacion {
    private double latitud;
    private double longitud;

    public Ubicacion() {
        // Constructor vacío requerido por Spring para deserialización JSON
    }

    public Ubicacion(double latitud, double longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
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
}
