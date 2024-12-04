package com.utndds.heladerasApi.services.Reportes.GeneradorReportes.Reportes;

import java.time.LocalDate;
import java.util.List;

import com.utndds.heladerasApi.models.Colaboraciones.Colaboracion;
import com.utndds.heladerasApi.models.Colaboraciones.DistribucionViandas;
import com.utndds.heladerasApi.models.Reportes.ViandasMovidasPorColaborador;
import com.utndds.heladerasApi.models.Rol.Colaborador;
import com.utndds.heladerasApi.repositories.ColaboradorRepository;
import com.utndds.heladerasApi.repositories.reportesRepositories.ReporteViandasMovidasColaboradorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReporteViandasPorColaboradorService implements GeneradorReporte {

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @Autowired
    private ReporteViandasMovidasColaboradorRepository reporteViandasColaboradorRepository;

    @Override
    public void generar(LocalDate fechaInicial, LocalDate fechaFinal) {
        List<Colaborador> colaboradores = colaboradorRepository.findAll();
        for (Colaborador colaborador : colaboradores) {
            int cantViandasDistribuidas = this.cantidadViandasDistribuidas(colaborador.getColaboraciones(), fechaInicial, fechaFinal);

            // Crear y guardar el reporte en la base de datos
            ViandasMovidasPorColaborador reporte = new ViandasMovidasPorColaborador(colaborador.getId(),
                    cantViandasDistribuidas);
            reporteViandasColaboradorRepository.save(reporte);

            System.out.println("Colaborador ID: " + colaborador.getId() + ", " + cantViandasDistribuidas
                    + " Viandas Distribuidas.");
        }
    }

    private int cantidadViandasDistribuidas(List<Colaboracion> colaboraciones, LocalDate fechaInicial, LocalDate fechaFinal) {
        int contador = 0;
        for (Colaboracion colaboracion : colaboraciones) {
            if (colaboracion instanceof DistribucionViandas) {
                DistribucionViandas distribucion = (DistribucionViandas) colaboracion;
                // Suponiendo que DistribucionViandas tiene una fecha
                if (distribucion.getFecha().isAfter(fechaInicial) && distribucion.getFecha().isBefore(fechaFinal)) {
                    contador += distribucion.getCantidadViandasAMover();
                }
            }
        }
        return contador;
    }
}