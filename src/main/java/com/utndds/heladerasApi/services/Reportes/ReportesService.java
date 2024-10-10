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
     * Obtener el reporte de fallas por heladera de la última semana
     */
    public List<FallasPorHeladera> obtenerReporteFallasUltimaSemana() {
        LocalDate inicioUltimaSemana = LocalDate.now().minusWeeks(1); // Inicio de la última semana
        return reporteFallasHeladeraRepository.findByFechaGeneracionAfter(inicioUltimaSemana);
    }

    /**
     * Obtener el reporte de viandas movidas por heladera de la última semana
     */
    public List<ViandasMovidasPorHeladera> obtenerReporteViandasHeladeraUltimaSemana() {
        LocalDate inicioUltimaSemana = LocalDate.now().minusWeeks(1); // Inicio de la última semana
        return reporteViandasHeladeraRepository.findByFechaGeneracionAfter(inicioUltimaSemana);
    }

    /**
     * Obtener el reporte de viandas distribuidas por colaborador de la última
     * semana
     */
    public List<ViandasMovidasPorColaborador> obtenerReporteViandasColaboradorUltimaSemana() {
        LocalDate inicioUltimaSemana = LocalDate.now().minusWeeks(1); // Inicio de la última semana
        return reporteViandasColaboradorRepository.findByFechaGeneracionAfter(inicioUltimaSemana);
    }

    /**
     * Obtener el reporte de una semana específica
     * 
     * param semanasAtras Número de semanas hacia atrás desde la fecha actual
     */
    public List<FallasPorHeladera> obtenerReporteFallasSemanaEspecifica(int semanasAtras) {
        LocalDate inicioSemana = LocalDate.now().minusWeeks(semanasAtras);
        LocalDate finSemana = inicioSemana.plusWeeks(1); // Fin de la semana
        return reporteFallasHeladeraRepository.findByFechaGeneracionBetween(inicioSemana, finSemana);
    }

    /**
     * Obtener un rango de reportes de viandas movidas por heladera
     * 
     * @param inicioFecha Fecha de inicio del rango
     * @param finFecha    Fecha de fin del rango
     */
    public List<ViandasMovidasPorHeladera> obtenerReportesViandasHeladeraPorRango(LocalDate inicioFecha,
            LocalDate finFecha) {
        return reporteViandasHeladeraRepository.findByFechaGeneracionBetween(inicioFecha, finFecha);
    }
}