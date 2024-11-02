package com.utndds.heladerasApi.DTOs;


import java.util.List;

public class ColaboradorDTO {
    private String uuid; // UUID del rol
    private String nombre;
    private String apellido;
    private String tipoDocumento;
    private String numeroDocumento;
    private String cuil;
    private double puntosGastados; // Puntos gastados por el colaborador
    private List<String> mediosContacto; // Lista de medios de contacto
    private String tarjeta; // ID o información de la tarjeta

    // Getter y Setter para UUID
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    // Getter y Setter para nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter y Setter para apellido
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    // Getter y Setter para tipoDocumento
    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    // Getter y Setter para numeroDocumento
    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    // Getter y Setter para cuil
    public String getCuil() {
        return cuil;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    // Getter y Setter para puntosGastados
    public double getPuntosGastados() {
        return puntosGastados;
    }

    public void setPuntosGastados(double puntosGastados) {
        this.puntosGastados = puntosGastados;
    }

    // Getter y Setter para mediosContacto
    public List<String> getMediosContacto() {
        return mediosContacto;
    }

    public void setMediosContacto(List<String> mediosContacto) {
        this.mediosContacto = mediosContacto;
    }

    // Getter y Setter para tarjeta
    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }
}

