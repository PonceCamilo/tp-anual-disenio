package com.utndds.heladerasApi.controllers.DTOs;

public class PuntoMapaDTO {

    private String nombre;
    private double latitud;
    private double longitud;
    private String provincia;
    private boolean funcionando;

    public PuntoMapaDTO(String nombre, double latitud, double longitud, String provincia, boolean funcionando) {
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
        this.provincia = provincia;
        this.funcionando = funcionando;
    }

    public String getNombre() {
        return nombre;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public String getProvincia() {
        return provincia;
    }

    public boolean isFuncionando() {
        return funcionando;
    }
}
