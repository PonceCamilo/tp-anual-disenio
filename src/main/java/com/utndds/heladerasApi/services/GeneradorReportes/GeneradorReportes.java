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
    List<Reporte> reportes = new ArrayList<>();

    public GeneradorReportes() {
        this.reportes.add(new FallasPorHeladera());
        this.reportes.add(new ViandasMovidasPorColaborador());
        this.reportes.add(new ViandasMovidasPorHeladera());
    }

    @Scheduled(fixedRateString = "604800000") // 604.800.000ms = 2 semanas
    public void generarReportes() {
        for (Reporte reporte : reportes) {
            reporte.generarReporte();
        }
    }

}
