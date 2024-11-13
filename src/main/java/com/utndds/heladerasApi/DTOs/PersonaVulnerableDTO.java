package com.utndds.heladerasApi.DTOs;

import java.time.LocalDate;

public class PersonaVulnerableDTO {
    String nombre;
    String apellido;
    LocalDate fechaNacimiento;
    boolean situacionCalle;
    String direccion;
    int cantMenoresAcargo;

    public PersonaVulnerableDTO(String nombre, boolean situacionCalle, int cantMenoresAcargo) {
        this.nombre = nombre;
        this.situacionCalle = situacionCalle;
        this.cantMenoresAcargo = cantMenoresAcargo;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean getSituacionCalle() {
        return situacionCalle;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getApellido() {
        return apellido;
    }

    public int getCantMenoresAcargo() {
        return cantMenoresAcargo;
    }

    public void setCantMenoresAcargo(int cantMenoresAcargo) {
        this.cantMenoresAcargo = cantMenoresAcargo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setSituacionCalle(boolean situacionCalle) {
        this.situacionCalle = situacionCalle;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

}
