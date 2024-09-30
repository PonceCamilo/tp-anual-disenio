package com.utndds.heladerasApi.repositories;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Heladera.Incidentes.FallaTecnica;
import java.util.List;

@Repository
public interface FallaTecnicaRepository extends JpaRepository<FallaTecnica, Long> {
    List<FallaTecnica> findByHeladeraAndFechaAfter(Heladera heladera, LocalDate fecha);
}