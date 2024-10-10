package com.utndds.heladerasApi.models.Heladera;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import com.utndds.heladerasApi.models.Colaboraciones.DonacionVianda;

@Setter
@Getter
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

    @OneToOne(mappedBy = "viandaDonada")
    private DonacionVianda donacionVianda;

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
    }

}
