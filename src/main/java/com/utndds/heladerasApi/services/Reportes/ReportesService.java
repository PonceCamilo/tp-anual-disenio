package com.utndds.heladerasApi.services.Reportes;

import com.utndds.heladerasApi.repositories.reportesRepositories.ReporteFallasHeladeraRepository;
import com.utndds.heladerasApi.repositories.reportesRepositories.ReporteViandasMovidasColaboradorRepository;
import com.utndds.heladerasApi.repositories.reportesRepositories.ReporteViandasMovidasHeladeraRepository;

import java.util.List;
import com.utndds.heladerasApi.models.Reportes.FallasPorHeladera;
import com.utndds.heladerasApi.models.Reportes.ViandasMovidasPorColaborador;
import com.utndds.heladerasApi.models.Reportes.ViandasMovidasPorHeladera;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportesService {

    @Autowired
    private ReporteFallasHeladeraRepository reporteFallasHeladeraRepository;

    @Autowired
    private ReporteViandasMovidasHeladeraRepository reporteViandasHeladeraRepository;

    @Autowired
    private ReporteViandasMovidasColaboradorRepository reporteViandasColaboradorRepository;

    /**
     * Obtener el reporte de fallas por heladera en un rango de fechas específico
     */
    public List<FallasPorHeladera> obtenerReporteFallasPorRango(LocalDate fechaInicial, LocalDate fechaFinal) {
        return reporteFallasHeladeraRepository.findByFechaGeneracionBetween(fechaInicial, fechaFinal);
    }

    /**
     * Obtener el reporte de viandas movidas por heladera en un rango de fechas
     * específico
     */
    public List<ViandasMovidasPorHeladera> obtenerReporteViandasHeladeraPorRango(LocalDate fechaInicial,
            LocalDate fechaFinal) {
        return reporteViandasHeladeraRepository.findByFechaGeneracionBetween(fechaInicial, fechaFinal);
    }

    /**
     * Obtener el reporte de viandas distribuidas por colaborador en un rango de
     * fechas específico
     */
    public List<ViandasMovidasPorColaborador> obtenerReporteViandasColaboradorPorRango(LocalDate fechaInicial,
            LocalDate fechaFinal) {
        return reporteViandasColaboradorRepository.findByFechaGeneracionBetween(fechaInicial, fechaFinal);
    }
}