package com.utndds.heladerasApi.repositories.reportesRepositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utndds.heladerasApi.models.Reportes.FallasPorHeladera;

@Repository
public interface ReporteFallasHeladeraRepository extends JpaRepository<FallasPorHeladera, Long> {

    List<FallasPorHeladera> findByFechaGeneracionAfter(LocalDate inicioUltimaSemana);
    // Puedes agregar métodos personalizados aquí si lo deseas

    List<FallasPorHeladera> findByFechaGeneracionBetween(LocalDate inicioSemana, LocalDate finSemana);
}