package com.utndds.heladerasApi.controllers.DTOs;

public class AreaRecomendacionDTO {
    double latitud;
    double longitud;
    double radio;

    public AreaRecomendacionDTO(double latitud, double longitud, double radio) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.radio = radio;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public double getRadio() {
        return radio;
    }
}
