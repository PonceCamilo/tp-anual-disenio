package com.utndds.heladerasApi.repositories.reportesRepositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utndds.heladerasApi.models.Reportes.FallasPorHeladera;

@Repository
public interface ReporteFallasHeladeraRepository extends JpaRepository<FallasPorHeladera, Long> {

    // Buscar reportes generados después de una fecha (última semana, por ejemplo)
    List<FallasPorHeladera> findByFechaGeneracionAfter(LocalDate inicioUltimaSemana);

    // Buscar reportes generados entre un rango de fechas (por ejemplo, semana
    // específica)
    List<FallasPorHeladera> findByFechaGeneracionBetween(LocalDate inicioSemana, LocalDate finSemana);
}