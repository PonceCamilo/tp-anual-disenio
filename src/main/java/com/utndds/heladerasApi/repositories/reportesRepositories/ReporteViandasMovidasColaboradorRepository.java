package com.utndds.heladerasApi.repositories.reportesRepositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.utndds.heladerasApi.models.Reportes.ViandasMovidasPorColaborador;

@Repository
public interface ReporteViandasMovidasColaboradorRepository extends JpaRepository<ViandasMovidasPorColaborador, Long> {

    // Buscar reportes generados después de una fecha (última semana, por ejemplo)
    List<ViandasMovidasPorColaborador> findByFechaGeneracionAfter(LocalDate inicioUltimaSemana);

    // Buscar reportes generados entre un rango de fechas (por ejemplo, semana
    // específica)
    List<ViandasMovidasPorColaborador> findByFechaGeneracionBetween(LocalDate inicioFecha, LocalDate finFecha);
}