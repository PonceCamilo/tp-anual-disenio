package com.utndds.heladerasApi.models.Heladera;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SensorTemperatura {
    Heladera heladera;
    double temperatura;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public SensorTemperatura(Heladera heladera, double temperatura) {
        this.heladera = heladera;
        this.temperatura = temperatura;
        scheduler.scheduleAtFixedRate(this::calcularTemp, 0, 5, TimeUnit.MINUTES);// se ejecuta c/5mins
    }

    private void calcularTemp() {
        Random random = new Random();
        this.temperatura = -10 + (20 + 10) * random.nextDouble();
        heladera.verificarTemp(this.temperatura);
    }

}
