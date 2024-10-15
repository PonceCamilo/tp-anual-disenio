package com.utndds.heladerasApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.utndds.heladerasApi.models.Reportes.*;
import com.utndds.heladerasApi.services.Reportes.ReportesService;
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
        List<FallasPorHeladera> reportes = reportesService.obtenerReporteFallasUltimaSemana();
        return ResponseEntity.ok(reportes);
    }

    /**
     * Obtener el reporte de viandas movidas por heladera de la última semana
     */
    @GetMapping("/viandas/heladera/ultima-semana")
    public ResponseEntity<List<ViandasMovidasPorHeladera>> obtenerReporteViandasHeladeraUltimaSemana() {
        List<ViandasMovidasPorHeladera> reportes = reportesService.obtenerReporteViandasHeladeraUltimaSemana();
        return ResponseEntity.ok(reportes);
    }

    /**
     * Obtener el reporte de viandas distribuidas por colaborador de la última
     * semana
     */
    @GetMapping("/viandas/colaborador/ultima-semana")
    public ResponseEntity<List<ViandasMovidasPorColaborador>> obtenerReporteViandasColaboradorUltimaSemana() {
        List<ViandasMovidasPorColaborador> reportes = reportesService.obtenerReporteViandasColaboradorUltimaSemana();
        return ResponseEntity.ok(reportes);
    }

    /**
     * Obtener el reporte de fallas por una semana específica
     * 
     * @param semanasAtras Número de semanas hacia atrás desde la fecha actual
     */
    @GetMapping("/fallas/semana/{semanasAtras}")
    public ResponseEntity<List<FallasPorHeladera>> obtenerReporteFallasSemanaEspecifica(
            @PathVariable int semanasAtras) {
        List<FallasPorHeladera> reportes = reportesService.obtenerReporteFallasSemanaEspecifica(semanasAtras);
        return ResponseEntity.ok(reportes);
    }
}