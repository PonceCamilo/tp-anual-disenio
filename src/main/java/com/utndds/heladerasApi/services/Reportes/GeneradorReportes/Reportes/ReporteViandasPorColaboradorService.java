package com.utndds.heladerasApi.services.Reportes.GeneradorReportes.Reportes;

import java.util.List;

import com.utndds.heladerasApi.models.Colaboraciones.Colaboracion;
import com.utndds.heladerasApi.models.Colaboraciones.DistribucionViandas;
import com.utndds.heladerasApi.models.Reportes.ViandasMovidasPorColaborador;
import com.utndds.heladerasApi.models.Rol.Colaborador;
import com.utndds.heladerasApi.repositories.ColaboradorRepository;
import com.utndds.heladerasApi.repositories.reportesRepositories.ReporteViandasMovidasColaboradorRepository;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReporteViandasPorColaboradorService implements GeneradorReporte {

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @Autowired
    private ReporteViandasMovidasColaboradorRepository reporteViandasColaboradorRepository;

    @Override
    public void generar() {
        List<Colaborador> colaboradores = colaboradorRepository.findAll();
        for (Colaborador colaborador : colaboradores) {
            int cantViandasDistribuidas = this.cantidadViandasDistribuidas(colaborador.getColaboraciones());

            // Crear y guardar el reporte en la base de datos
            ViandasMovidasPorColaborador reporte = new ViandasMovidasPorColaborador(LocalDate.now(),
                    colaborador.getId(), cantViandasDistribuidas);
            reporteViandasColaboradorRepository.save(reporte);

            System.out.println("Colaborador ID: " + colaborador.getId() + ", " + cantViandasDistribuidas
                    + " Viandas Distribuidas.");
        }
    }

    private int cantidadViandasDistribuidas(List<Colaboracion> colaboraciones) {
        int contador = 0;
        for (Colaboracion colaboracion : colaboraciones) {
            if (colaboracion instanceof DistribucionViandas) {
                DistribucionViandas distribucion = (DistribucionViandas) colaboracion;
                contador += distribucion.getCantidadViandasAMover();
            }
        }
        return contador;
    }
}