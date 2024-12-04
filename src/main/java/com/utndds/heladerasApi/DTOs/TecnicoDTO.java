package com.utndds.heladerasApi.DTOs;

import java.util.List;

import com.utndds.heladerasApi.models.Rol.Tecnico.AreaCobertura;

public class TecnicoDTO {
    private String nombre;
    private String apellido;
    private String tipoDocumento;
    private String numeroDocumento;
    private String cuil;
    private List<String> mediosContacto;
    private AreaCobertura areaCobertura;
    private String uuid;
    
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getCuil() {
        return cuil;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public List<String> getMediosContacto() {
        return mediosContacto;
    }

    public void setMediosContacto(List<String> mediosContacto) {
        this.mediosContacto = mediosContacto;
    }

    public AreaCobertura getAreaCobertura() {
        return areaCobertura;
    }

    public void setAreaCobertura(AreaCobertura areaCobertura) {
        this.areaCobertura = areaCobertura;
    }
}