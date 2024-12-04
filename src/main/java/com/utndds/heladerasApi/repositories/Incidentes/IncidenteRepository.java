package com.utndds.heladerasApi.repositories.Incidentes;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.utndds.heladerasApi.models.Heladera.Incidentes.Incidente.Incidente;

@Repository
public interface IncidenteRepository extends JpaRepository<Incidente, Long> {
    Optional<Incidente> findByid(Long id);
}