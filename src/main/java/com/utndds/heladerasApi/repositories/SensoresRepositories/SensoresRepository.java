package com.utndds.heladerasApi.repositories.SensoresRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.utndds.heladerasApi.models.Heladera.Sensores.Sensor;

@Repository
public interface SensoresRepository extends JpaRepository<Sensor, Long> {
    // Puedes agregar métodos personalizados aquí si lo deseas
}