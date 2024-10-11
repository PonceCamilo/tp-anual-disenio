package com.utndds.heladerasApi.models.Colaboraciones.Ofertas;

import com.utndds.heladerasApi.models.Rol.Colaborador;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "canje")
public class Canje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID único para cada canje

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "colaborador", nullable = false)
    private Colaborador colaborador; // Colaborador que realiza el canje

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "oferta", nullable = false)
    private Oferta oferta; // Oferta canjeada

    @Column(name = "puntos_gastados", nullable = false)
    private double puntosGastados; // Puntos gastados en el canje

    // Constructor vacío para JPA
    public Canje() {
    }

    // Constructor para crear un canje
    public Canje(Colaborador colaborador, Oferta oferta, double puntosGastados) {
        this.colaborador = colaborador;
        this.oferta = oferta;
        this.puntosGastados = puntosGastados;

        this.sumarPuntosGastados();
    }

    private void sumarPuntosGastados() {
        this.colaborador.sumarPuntosGastados(puntosGastados);

    }
}