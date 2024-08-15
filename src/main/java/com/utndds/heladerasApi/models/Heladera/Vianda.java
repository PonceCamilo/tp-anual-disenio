package com.utndds.heladerasApi.models.Heladera;

import javax.persistence.*;

import java.time.LocalDate;

@Entity
public class Vianda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comida")
    private String comida;

    @Column(name = "fecha_caducidad")
    private LocalDate fechaCaducidad;

    @Column(name = "calorias")
    private double calorias;

    @Column(name = "peso")
    private double peso;

    @Column(name = "estado")
    private boolean estado;

    @ManyToOne
    @JoinColumn(name = "heladera", referencedColumnName = "id")
    private Heladera heladera;

    // Constructor vacío para JPA
    public Vianda() {
    }

    public Vianda(String comida, LocalDate fechaCaducidad, double calorias, double peso, boolean estado,
            Heladera heladera) {
        this.comida = comida;
        this.fechaCaducidad = fechaCaducidad;
        this.calorias = calorias;
        this.peso = peso;
        this.estado = estado;
        this.heladera = heladera;
        this.heladera.agregarVianda(this);
    }

}
