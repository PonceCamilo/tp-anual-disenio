package com.utndds.heladerasApi.services.Sensores;

import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Heladera.Sensores.SensorTemperatura;

import org.springframework.stereotype.Service;

import com.utndds.heladerasApi.repositories.HeladeraRepository;
import com.utndds.heladerasApi.repositories.SensoresRepositories.SensorTemperaturaRepository;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class SensorTemperaturaService {
    @Autowired
    private SensorTemperaturaRepository sensorTemperaturaRepository;
    @Autowired
    private HeladeraRepository heladeraRepository;

    public void procesarTemperatura(Long sensorId, Double nuevaTemperatura) {
        // Buscar el sensor por ID
        SensorTemperatura sensor = sensorTemperaturaRepository.findById(sensorId)
                .orElseThrow(() -> new RuntimeException("Sensor no encontrado con ID: " + sensorId));

        // Actualizar la temperatura del sensor
        sensor.actualizarTemperatura(nuevaTemperatura);
        Heladera heladera = sensor.getHeladera(); // Obtener la heladera

        // Guardar la heladera si se han realizado cambios
        sensorTemperaturaRepository.save(sensor);
        heladeraRepository.save(heladera);
    }
}