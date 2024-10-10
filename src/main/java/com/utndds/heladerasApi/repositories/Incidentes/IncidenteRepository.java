package com.utndds.heladerasApi.repositories.Incidentes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.utndds.heladerasApi.models.Heladera.Incidentes.Incidente.Incidente;

@Repository
public interface IncidenteRepository extends JpaRepository<Incidente, Long> {
    // Puedes agregar métodos personalizados aquí si lo deseas
}