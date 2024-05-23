package com.utndds.heladerasApi.models.Heladera;

import java.util.Random;

public class SensorTemperatura {
    Heladera heladera;
    double temperatura;

    public SensorTemperatura(Heladera heladera, double temperatura) {
        this.heladera = heladera;
        this.temperatura = temperatura;
    }

    public void actualizarTemp() {
        Random random = new Random();
        this.temperatura = -10 + (20 + 10) * random.nextDouble();
        this.verificarTemp();
    }

    private void verificarTemp() {
        double tempMin = heladera.getMinTemp();
        double tempMax = heladera.getMaxTemp();
        if (this.temperatura < tempMin || this.temperatura > tempMax) {
            heladera.setEstado(false);
        }
    }
}
