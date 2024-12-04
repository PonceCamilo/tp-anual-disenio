package com.utndds.heladerasApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.utndds.heladerasApi.models.Reportes.*;
import com.utndds.heladerasApi.services.Reportes.ReportesService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reportes")
public class ReporteController {

    @Autowired
    private ReportesService reportesService;

    /**
     * Obtener el reporte de fallas por heladera de la última semana
     */
    @GetMapping("/fallas/ultima-semana")
    public ResponseEntity<List<FallasPorHeladera>> obtenerReporteFallasUltimaSemana() {
        LocalDate fechaInicial = LocalDate.now().minusWeeks(1);
        LocalDate fechaFinal = LocalDate.now();
        List<FallasPorHeladera> reportes = reportesService.obtenerReporteFallasPorRango(fechaInicial, fechaFinal);
        return ResponseEntity.ok(reportes);
    }

    /**
     * Obtener el reporte de viandas movidas por heladera de la última semana
     */
    @GetMapping("/viandas/heladera/ultima-semana")
    public ResponseEntity<List<ViandasMovidasPorHeladera>> obtenerReporteViandasHeladeraUltimaSemana() {
        LocalDate fechaInicial = LocalDate.now().minusWeeks(1);
        LocalDate fechaFinal = LocalDate.now();
        List<ViandasMovidasPorHeladera> reportes = reportesService.obtenerReporteViandasHeladeraPorRango(fechaInicial,
                fechaFinal);
        return ResponseEntity.ok(reportes);
    }

    /**
     * Obtener el reporte de viandas distribuidas por colaborador de la última
     * semana
     */
    @GetMapping("/viandas/colaborador/ultima-semana")
    public ResponseEntity<List<ViandasMovidasPorColaborador>> obtenerReporteViandasColaboradorUltimaSemana() {
        LocalDate fechaInicial = LocalDate.now().minusWeeks(1);
        LocalDate fechaFinal = LocalDate.now();
        List<ViandasMovidasPorColaborador> reportes = reportesService
                .obtenerReporteViandasColaboradorPorRango(fechaInicial, fechaFinal);
        return ResponseEntity.ok(reportes);
    }

    /**
     * Obtener el reporte de fallas por un rango de fechas específico
     * 
     * @param fechaInicial Fecha de inicio del rango (formato: YYYY-MM-DD)
     * @param fechaFinal   Fecha de fin del rango (formato: YYYY-MM-DD)
     */
    @GetMapping("/fallas/rango")
    public ResponseEntity<List<FallasPorHeladera>> obtenerReporteFallasPorRango(
            @RequestParam("fechaInicial") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicial,
            @RequestParam("fechaFinal") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFinal) {
        List<FallasPorHeladera> reportes = reportesService.obtenerReporteFallasPorRango(fechaInicial, fechaFinal);
        return ResponseEntity.ok(reportes);
    }

    /**
     * Obtener el reporte de viandas movidas por heladera en un rango de fechas
     * 
     * @param fechaInicial Fecha de inicio del rango (formato: YYYY-MM-DD)
     * @param fechaFinal   Fecha de fin del rango (formato: YYYY-MM-DD)
     */
    @GetMapping("/viandas/heladera/rango")
    public ResponseEntity<List<ViandasMovidasPorHeladera>> obtenerReporteViandasHeladeraPorRango(
            @RequestParam("fechaInicial") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicial,
            @RequestParam("fechaFinal") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFinal) {
        List<ViandasMovidasPorHeladera> reportes = reportesService.obtenerReporteViandasHeladeraPorRango(fechaInicial,
                fechaFinal);
        return ResponseEntity.ok(reportes);
    }

    /**
     * Obtener el reporte de viandas distribuidas por colaborador en un rango de
     * fechas
     * 
     * @param fechaInicial Fecha de inicio del rango (formato: YYYY-MM-DD)
     * @param fechaFinal   Fecha de fin del rango (formato: YYYY-MM-DD)
     */
    @GetMapping("/viandas/colaborador/rango")
    public ResponseEntity<List<ViandasMovidasPorColaborador>> obtenerReporteViandasColaboradorPorRango(
            @RequestParam("fechaInicial") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicial,
            @RequestParam("fechaFinal") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFinal) {
        List<ViandasMovidasPorColaborador> reportes = reportesService
                .obtenerReporteViandasColaboradorPorRango(fechaInicial, fechaFinal);
        return ResponseEntity.ok(reportes);
    }
}