package com.example.demo.models.Tarjetas;

import java.time.LocalDate;

import com.example.demo.models.Enums.MotivoApertura;
import com.example.demo.models.Heladera.Heladera;

import jakarta.persistence.*;

@Entity
public class Apertura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "heladera")
    private Heladera heladera;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tarjeta")
    private Tarjeta tarjeta;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "motivo")
    private MotivoApertura motivo;

    // Constructor vacío para JPA
    public Apertura() {
    }

    public Apertura(Heladera heladera, Tarjeta tarjeta, MotivoApertura motivoApertura) {
        this.heladera = heladera;
        this.tarjeta = tarjeta;
        this.motivo = motivoApertura;
        this.fecha = LocalDate.now();

    }

}
