package com.utndds.heladerasApi.services.Reportes.GeneradorReportes;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.utndds.heladerasApi.services.Reportes.GeneradorReportes.Reportes.GeneradorReporte;

import java.time.LocalDate;
import java.util.List;

@Service
public class GeneradorReportesService {

    private final List<GeneradorReporte> reportes;

    public GeneradorReportesService(List<GeneradorReporte> reportes) {
        this.reportes = reportes;
    }

    // Este método ahora puede aceptar un rango de fechas para mayor flexibilidad
    @Scheduled(cron = "0 0 0 * * SUN")
    public void generarReportesSemanales() {
        LocalDate fechaFinal = LocalDate.now(); // Hoy
        LocalDate fechaInicial = fechaFinal.minusWeeks(1); // La semana pasada

        generarReportes(fechaInicial, fechaFinal);
    }

    // Método que genera los reportes en un rango de fechas determinado
    public void generarReportes(LocalDate fechaInicial, LocalDate fechaFinal) {
        for (GeneradorReporte reporte : reportes) {
            try {
                reporte.generar(fechaInicial, fechaFinal); // Pasamos el rango de fechas a cada reporte
            } catch (Exception e) {
                // Manejo de excepciones
                System.err.println(
                        "Error generando reporte: " + reporte.getClass().getSimpleName() + ", " + e.getMessage());
            }
        }
    }
}