package com.utndds.heladerasApi.services.GeneradorReportes;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.List;
import com.utndds.heladerasApi.services.GeneradorReportes.Reportes.Reporte;

@Service
public class GeneradorReportes {

    private final List<Reporte> reportes;

    public GeneradorReportes(List<Reporte> reportes) {
        this.reportes = reportes;
    }

    @Scheduled(cron = "0 0 0 * * SUN")
    public void generarReportesSemanales() {
        for (Reporte reporte : reportes) {
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