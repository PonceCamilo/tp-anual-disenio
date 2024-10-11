package com.utndds.heladerasApi.DTOs;

public class PersonaVulnerableDTO {
    String nombre;
    boolean situacionCalle;
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

    public int getCantMenoresAcargo() {
        return cantMenoresAcargo;
    }

    public void setCantMenoresAcargo(int cantMenoresAcargo) {
        this.cantMenoresAcargo = cantMenoresAcargo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSituacionCalle(boolean situacionCalle) {
        this.situacionCalle = situacionCalle;
    }

}
