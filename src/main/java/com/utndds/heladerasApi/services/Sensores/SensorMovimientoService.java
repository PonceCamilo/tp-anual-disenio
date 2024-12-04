package com.utndds.heladerasApi.services.Sensores;

import com.utndds.heladerasApi.models.Heladera.Incidentes.Incidente.Alerta;
import com.utndds.heladerasApi.models.Heladera.Sensores.SensorMovimiento;

import org.springframework.stereotype.Service;

import com.utndds.heladerasApi.repositories.HeladeraRepository;
import com.utndds.heladerasApi.repositories.Incidentes.IncidenteRepository;
import com.utndds.heladerasApi.repositories.SensoresRepositories.SensorMovimientoRepository;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class SensorMovimientoService {

    @Autowired
    private SensorMovimientoRepository sensorMovimientoRepository; // Asegúrate de que esto es correcto
    @Autowired
    private IncidenteRepository incidenteRepository; // Repositorio para manejar Alertas
    @Autowired
    private HeladeraRepository heladeraRepository; // Repositorio para manejar Alertas

    public void generarAlerta(Long sensorId, String tipoAlerta) {
        // Buscar la heladera por ID
        SensorMovimiento sensor = sensorMovimientoRepository.findById(sensorId)
                .orElseThrow(() -> new RuntimeException("sensor no encontrado con ID: " + sensorId));

        // Crear la alerta
        Alerta alerta = new Alerta(sensor.getHeladera(), tipoAlerta);

        // Guardar la alerta en la base de datos
        incidenteRepository.save(alerta);
        heladeraRepository.save(alerta.getHeladera());

        System.out.println(
                "Alerta generada para la heladera ID: " + sensor.getHeladera().getId() + " de tipo: " + tipoAlerta);
    }
}
