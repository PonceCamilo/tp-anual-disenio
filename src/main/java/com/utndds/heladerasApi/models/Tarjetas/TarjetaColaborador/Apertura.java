package com.utndds.heladerasApi.models.Tarjetas.TarjetaColaborador;

import java.time.LocalDate;

import com.utndds.heladerasApi.models.Heladera.Heladera;
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
    @JoinColumn(name = "tarjeta_colaborador")
    private TarjetaColaborador tarjetaColaborador;

    @Column(name = "fecha")
    private LocalDate fecha;

    // Constructor vacío para JPA
    public Apertura() {
    }

    public Apertura(Heladera heladera, TarjetaColaborador tarjetaColaborador) {
        this.heladera = heladera;
        this.tarjetaColaborador = tarjetaColaborador;
        this.fecha = LocalDate.now();
        this.procesar();

    }

    private void procesar() {
        this.tarjetaColaborador.agregarApertura(this);
    }

}
