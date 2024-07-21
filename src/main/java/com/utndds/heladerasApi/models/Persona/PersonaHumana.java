package com.utndds.heladerasApi.models.Persona;

import com.utndds.heladerasApi.models.Colaboraciones.Colaboracion;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;

public class PersonaHumana extends Persona {
    private String nombre;
    private String apellido;
    private LocalDate fechaDeNacimiento;
    private List<Colaboracion> posiblesColaboraciones = new ArrayList<>();

    public PersonaHumana() {
        // Constructor
    }

    public PersonaHumana(String nombre, String apellido, LocalDate fechaDeNacimiento) {
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

    public LocalDate getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public List<Colaboracion> getPosiblesColaboraciones() {
        return posiblesColaboraciones;
    }

    public void setPosiblesColaboraciones(List<Colaboracion> posiblesColaboraciones) {
        this.posiblesColaboraciones = posiblesColaboraciones;
    }
}
