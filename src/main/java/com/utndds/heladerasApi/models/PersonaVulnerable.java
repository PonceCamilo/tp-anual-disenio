package com.utndds.heladerasApi.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersonaVulnerable {
    String nombre;
    LocalDate fechaNacimiento;
    LocalDate fechaRegistro;
    boolean situacionCalle;
    String domicilio;
    Documento documento;
    int cantMenoresAcargo;
    List<UsoTarjeta> usosTarjeta = new ArrayList<>();

    public PersonaVulnerable(
            String nombre,
            LocalDate fechaNacimiento,
            LocalDate fechaRegistro,
            boolean situacionCalle,
            String domicilio,
            Documento documento,
            int cantMenoresAcargo,
            List<UsoTarjeta> usosTarjeta) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaRegistro = fechaRegistro;
        this.situacionCalle = situacionCalle;
        this.domicilio = domicilio;
        this.documento = documento;
        this.cantMenoresAcargo = cantMenoresAcargo;
        this.usosTarjeta = usosTarjeta;
    }

    public void alta() {
        System.out.println("Se realizo alta de la personaVulnerable: " + this.nombre);
    }

    public void usarHeladera(Heladera heladera) {
        if (heladera.cantViandasDentro() > 0) {
            if (this.puedeUsarTarjeta()) {

                System.out.println("La personaVulnerable: " + this.nombre + " sacó una vianda");
                this.usosTarjeta.add(new UsoTarjeta(heladera, LocalDate.now()));
            } else {
                System.out.println("No puede usar la tarjeta (exceso de usos diarios)");
            }
        } else {
            System.out.println("No hay viandas en la heladera");
        }
    }

    private boolean puedeUsarTarjeta() {
        return this.cantUsosHoy() < this.cantUsosDiariosPermitidos();
    }

    private int cantUsosHoy() {
        int cantUsos = 0;
        LocalDate hoy = LocalDate.now();

        for (UsoTarjeta uso : usosTarjeta) {
            if (uso.getFecha().equals(hoy)) {
                cantUsos++;
            }
        }
        return cantUsos;
    }

    private int cantUsosDiariosPermitidos() {
        return 4 + 2 * this.cantMenoresAcargo;
    }
}
