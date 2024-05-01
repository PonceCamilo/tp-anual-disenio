package com.utndds.heladerasApi.models;
import java.time.LocalDate;

public class Vianda {
    String comida;
    LocalDate fechaCaducidad;
    double calorias;
    double peso;

    public Vianda(String comida, LocalDate fechaCaducidad, double calorias, double peso) {
        this.comida = comida;
        this.fechaCaducidad = fechaCaducidad;
        this.calorias = calorias;
        this.peso = peso;
    }

}
