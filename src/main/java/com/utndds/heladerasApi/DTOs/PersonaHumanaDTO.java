package com.utndds.heladerasApi.DTOs;

import java.time.LocalDate;
import java.util.List;

public class PersonaHumanaDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private List<String> mediosContacto;
    private String email;
    private String whatsapp;
    private String telegram;
    private LocalDate  fechaNacimiento;
    private String direccion;
    private String documento;
    private String tipo;

    

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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

    // Getter y Setter para mediosContacto

    public List<String> getMediosContacto() {
        return mediosContacto;
    }

    public void setMediosContacto(List<String> mediosContacto) {
        this.mediosContacto = mediosContacto;
    }

    // Getter y Setter para email

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
    // Getter y Setter para fechaNacimiento

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    // Getter y Setter para direccion
    
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    // Getter y Setter para documento
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public PersonaHumanaDTO(Long id, String nombre, String apellido, List<String> mediosContacto, LocalDate fechaNacimiento, String direccion, String documento) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.mediosContacto = mediosContacto;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.documento = documento;
    }
}
