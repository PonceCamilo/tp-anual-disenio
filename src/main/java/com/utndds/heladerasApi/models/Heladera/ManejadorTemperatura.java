package com.utndds.heladerasApi.models.Heladera;

import javax.persistence.*;

import com.utndds.heladerasApi.models.Heladera.Incidentes.Alerta;

@Entity
public class ManejadorTemperatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "heladera", referencedColumnName = "id")
    private Heladera heladera;

    @Column(name = "ultima_temp_registrada")
    private double ultimaTempRegistrada;

    @Column(name = "minima_temperatura")
    private double minTemp;

    @Column(name = "maxima_temperatura")
    private double maxTemp;

    // Constructor vacío para JPA
    public ManejadorTemperatura() {
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
