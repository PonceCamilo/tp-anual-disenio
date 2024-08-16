package com.utndds.heladerasApi.models.Tarjetas.TarjetaPersVuln;

import com.utndds.heladerasApi.models.Heladera.Heladera;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
public class UsoHeladera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "heladera")
    private Heladera heladera;

    @ManyToOne
    @JoinColumn(name = "tarjeta_pers_vuln")
    private TarjetaPersVuln tarjeta;

    @Column(name = "fecha_uso")
    private LocalDate fechaUso;

    // Constructor vacío para JPA
    public UsoHeladera() {
    }

    public UsoHeladera(Heladera heladera, TarjetaPersVuln tarjeta) {
        this.heladera = heladera;
        this.tarjeta = tarjeta;
        this.fechaUso = LocalDate.now();
        this.procesar();
    }

    private void procesar() {
        this.tarjeta.agregarUso(this);
        this.heladera.extraerVianda();
    }

    public void setTarjeta(TarjetaPersVuln tarjeta) {
        this.tarjeta = tarjeta;
    }

    public TarjetaPersVuln getTarjeta() {
        return tarjeta;
    }

    public void setFechaUso(LocalDate fechaUso) {
        this.fechaUso = fechaUso;
    }

    public LocalDate getFechaUso() {
        return fechaUso;
    }

    public void setHeladera(Heladera heladera) {
        this.heladera = heladera;
    }

    public Heladera getHeladera() {
        return heladera;
    }

}
