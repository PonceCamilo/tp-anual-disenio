package com.utndds.heladerasApi.models.Persona;

import com.utndds.heladerasApi.models.Colaboraciones.Colaboracion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PersonaHumana extends Persona {
    private String nombre;
    private String apellido;
    private Date fechaDeNacimiento;
    private List<Colaboracion> posiblesColaboraciones = new ArrayList<>();

    public PersonaHumana() {
        // Constructor
    }

    public PersonaHumana(String nombre, String apellido, Date fechaDeNacimiento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    @Override
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
