package com.utndds.heladerasApi.services.Reportes.GeneradorReportes;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.utndds.heladerasApi.services.Reportes.GeneradorReportes.Reportes.GeneradorReporte;

import java.util.List;

@Service
public class GeneradorReportesService {

    private final List<GeneradorReporte> reportes;

    public GeneradorReportesService(List<GeneradorReporte> reportes) {
        this.reportes = reportes;
    }

    @Scheduled(cron = "0 0 0 * * SUN")
    public void generarReportesSemanales() {
        for (GeneradorReporte reporte : reportes) {
            try {
                reporte.generar();
            } catch (Exception e) {
                // Manejo de excepciones
                System.err.println(
                        "Error generando reporte: " + reporte.getClass().getSimpleName() + ", " + e.getMessage());
            }
        }
    }
}