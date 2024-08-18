package com.utndds.heladerasApi.services.broker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.utndds.heladerasApi.models.Heladera.Sensores.SensorTemperatura;

@Service
public class BrokerSensorTemp {
    private final List<SensorTemperatura> sensores = new ArrayList<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public BrokerSensorTemp() {
        // Programa la tarea para ejecutar cada 5 minutos
        scheduler.scheduleAtFixedRate(this::recibirTemperaturas, 0, 5, TimeUnit.MINUTES);
    }

    // Registra un sensor en el broker
    public void registrarSensor(SensorTemperatura sensor) {
        sensores.add(sensor);
    }

    // metodo que recibe la temperatura de todos los sensores registrados
    private void recibirTemperaturas() {
        for (SensorTemperatura sensor : sensores) {
            // Aquí puedes simular la recepción de una temperatura para cada sensor
            double temperatura = obtenerTemperaturaDesdeSistemaExterno(sensor.getId());

            // Actualizar la temperatura del sensor
            sensor.actualizarTemperatura(temperatura);
        }
    }

    // simula la obtención de temperatura desde el sistema externo
    private double obtenerTemperaturaDesdeSistemaExterno(Long id) {
        return Math.random() * 100; // Temperatura simulada
    }
}