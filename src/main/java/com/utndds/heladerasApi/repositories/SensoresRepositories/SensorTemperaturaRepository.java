package com.utndds.heladerasApi.repositories.SensoresRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utndds.heladerasApi.models.Heladera.Sensores.SensorTemperatura;

@Repository
public interface SensorTemperaturaRepository extends JpaRepository<SensorTemperatura, Long> {
    // Puedes agregar métodos personalizados aquí si lo deseas
}