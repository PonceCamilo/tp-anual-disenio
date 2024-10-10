package com.utndds.heladerasApi.repositories.SensoresRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.utndds.heladerasApi.models.Heladera.Sensores.SensorMovimiento;

@Repository
public interface SensorMovimientoRepository extends JpaRepository<SensorMovimiento, Long> {
    // Puedes agregar métodos personalizados aquí si lo deseas
}