package com.utndds.heladerasApi.DTOs;
import java.util.List;

public class PersonaJuridicaDTO {
    
    private String razonSocial;
    private String tipo;
    private String rubro;
    private String direccion;
    private List<String> mediosContacto;
    private String email;
    private String whatsapp;
    private String telegram;

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getTelegram() {
        return telegram;
    }

    public void setTelegram(String telegram) {
        this.telegram = telegram;
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
    }

    public String getRubro() {
        return rubro;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setMediosContacto(List<String> mediosContacto) {
        this.mediosContacto = mediosContacto;
    }

    public List<String> getMediosContacto() {
        return mediosContacto;
    }

    public PersonaJuridicaDTO(String direccion, List<String> mediosContacto, String razonSocial, String tipo, String rubro) {
        this.razonSocial = razonSocial;
        this.tipo = tipo;
        this.rubro = rubro;
        this.direccion = direccion;
        this.mediosContacto = mediosContacto;
    }

}
