package com.utndds.heladerasApi.models.Heladera;

import com.utndds.heladerasApi.models.Heladera.Incidentes.Alerta;

public class ManejadorTemperatura {
    Heladera heladera;
    double ultimaTempRegistrada;
    double minTemp;
    double maxTemp;

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
