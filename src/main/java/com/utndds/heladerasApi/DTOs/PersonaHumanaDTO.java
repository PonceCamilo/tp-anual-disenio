package com.utndds.heladerasApi.DTOs;

import java.sql.Date;
import java.util.List;
import com.utndds.heladerasApi.models.Persona.Documento;

public class PersonaHumanaDTO {
    private Long id;
    private String nombre;
    private String apellido;
    //private Contribuir
    private List<String> mediosContacto;
    private Date  fechaNacimiento;
    private String direccion;
    private Documento documento;

    

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

    // Getter y Setter para fechaNacimiento

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
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

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public PersonaHumanaDTO(Long id, String nombre, String apellido, List<String> mediosContacto, Date fechaNacimiento, String direccion, Documento documento) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.mediosContacto = mediosContacto;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.documento = documento;
    }
}
