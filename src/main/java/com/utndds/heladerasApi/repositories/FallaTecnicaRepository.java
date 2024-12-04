package com.utndds.heladerasApi.repositories;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Heladera.Incidentes.Incidente.FallaTecnica;
import java.util.List;
import java.util.Optional;

@Repository
public interface FallaTecnicaRepository extends JpaRepository<FallaTecnica, Long> {
    List<FallaTecnica> findByHeladeraAndFechaAfter(Heladera heladera, LocalDate fecha);

    // Buscar las fallas de una heladera entre dos fechas
    List<FallaTecnica> findByHeladeraAndFechaBetween(Heladera heladera, LocalDate fechaInicial, LocalDate fechaFinal);

    // Cambiar findByid a findById (la "I" en mayúscula)
    @SuppressWarnings("null")
    Optional<FallaTecnica> findById(Long id);
}