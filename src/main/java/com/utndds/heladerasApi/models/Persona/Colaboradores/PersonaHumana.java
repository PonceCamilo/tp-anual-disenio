package com.utndds.heladerasApi.models.Persona.Colaboradores;

import com.utndds.heladerasApi.models.Colaboraciones.Colaboracion;
import com.utndds.heladerasApi.models.Persona.Contacto.Contacto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PersonaHumana extends Colaborador {
    private String nombre;
    private String apellido;
    private Date fechaDeNacimiento;
    private List<Colaboracion> posiblesColaboraciones = new ArrayList<>();

    public PersonaHumana() {
        // Constructor
    }

    public PersonaHumana(List<Contacto> mediosDeContacto, String direccion, List<Colaboracion> colaboraciones,
            String nombre, String apellido, Date fechaDeNacimiento) {
        super(mediosDeContacto, direccion, colaboraciones);
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    // Getters y Setters
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

    public Date getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Date fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public List<Colaboracion> getPosiblesColaboraciones() {
        return posiblesColaboraciones;
    }

    public void setPosiblesColaboraciones(List<Colaboracion> posiblesColaboraciones) {
        this.posiblesColaboraciones = posiblesColaboraciones;
    }
}
