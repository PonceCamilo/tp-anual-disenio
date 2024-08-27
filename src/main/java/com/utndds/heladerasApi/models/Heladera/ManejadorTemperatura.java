package com.utndds.heladerasApi.models.Heladera;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import com.utndds.heladerasApi.models.Heladera.Incidentes.Alerta;

@Setter
@Getter
@Entity
public class ManejadorTemperatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "manejadorTemperatura", fetch = FetchType.LAZY)
    private Heladera heladera;

    @Column(name = "ultima_temp_registrada")
    private double ultimaTempRegistrada;

    @Column(name = "minima_temperatura")
    private double minTemp;

    @Column(name = "maxima_temperatura")
    private double maxTemp;

    public ManejadorTemperatura() {
        // Constructor vacío
    }

    public ManejadorTemperatura(Heladera heladera, double minTemp, double maxTemp) {
        this.heladera = heladera;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
    }

    public void actualizarTemperatura(double temperatura) {
        this.ultimaTempRegistrada = temperatura;
        this.verificarTemperatura();
    }

    private void verificarTemperatura() {
        if (this.ultimaTempRegistrada < this.minTemp || this.ultimaTempRegistrada > this.maxTemp) {
            new Alerta(this.heladera, "Temperatura");
        }
    }
}
