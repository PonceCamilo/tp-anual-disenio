package com.utndds.heladerasApi.repositories.reportesRepositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utndds.heladerasApi.models.Reportes.ViandasMovidasPorHeladera;

@Repository
public interface ReporteViandasMovidasHeladeraRepository extends JpaRepository<ViandasMovidasPorHeladera, Long> {

    List<ViandasMovidasPorHeladera> findByFechaGeneracionAfter(LocalDate inicioUltimaSemana);
    // Puedes agregar métodos personalizados aquí si lo deseas

    List<ViandasMovidasPorHeladera> findByFechaGeneracionBetween(LocalDate inicioFecha, LocalDate finFecha);
}