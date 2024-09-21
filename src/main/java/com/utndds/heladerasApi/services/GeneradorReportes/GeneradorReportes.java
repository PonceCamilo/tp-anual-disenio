package com.utndds.heladerasApi.services.GeneradorReportes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.utndds.heladerasApi.services.GeneradorReportes.Reportes.FallasPorHeladera;
import com.utndds.heladerasApi.services.GeneradorReportes.Reportes.Reporte;
import com.utndds.heladerasApi.services.GeneradorReportes.Reportes.ViandasMovidasPorColaborador;
import com.utndds.heladerasApi.services.GeneradorReportes.Reportes.ViandasMovidasPorHeladera;

@Service
public class GeneradorReportes {
    List<Reporte> reportesSemanales = new ArrayList<>();

    public GeneradorReportes() {
        this.reportesSemanales.add(new FallasPorHeladera());
        this.reportesSemanales.add(new ViandasMovidasPorColaborador());
        this.reportesSemanales.add(new ViandasMovidasPorHeladera());
    }

    private void generarReportes(List<Reporte> listaReportes) {
        for (Reporte reporte : listaReportes) {
            reporte.generarReporte();
        }
    }

    @Scheduled(cron = "0 0 0 * * SUN")
    public void generarReportesSemanales() {
        generarReportes(reportesSemanales);
    }

}
