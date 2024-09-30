
package com.utndds.heladerasApi.services.GeneradorReportes.Reportes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Reportes.FallasPorHeladera;
import com.utndds.heladerasApi.repositories.FallaTecnicaRepository;
import com.utndds.heladerasApi.repositories.HeladeraRepository;
import com.utndds.heladerasApi.repositories.reportesRepositories.ReporteFallasHeladeraRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReporteFallasPorHeladeraService implements Reporte {

    @Autowired
    private FallaTecnicaRepository fallaTecnicaRepository;

    @Autowired
    private HeladeraRepository heladeraRepository;

    @Autowired
    private ReporteFallasHeladeraRepository reporteFallaRepository; // Repositorio para guardar los reportes

    @Override
    public void generar() {
        LocalDate unaSemanaAtras = LocalDate.now().minusWeeks(1);
        List<Heladera> heladeras = heladeraRepository.findAll();

        for (Heladera heladera : heladeras) {
            int cantFallas = fallaTecnicaRepository.findByHeladeraAndFechaAfter(heladera, unaSemanaAtras).size();

            // Crear y guardar el reporte en la base de datos
            FallasPorHeladera reporteFalla = new FallasPorHeladera(LocalDate.now(), heladera.getId(), cantFallas);
            reporteFallaRepository.save(reporteFalla); // Guardar en la base de datos

            System.out.println("Heladera ID: " + heladera.getPunto().getNombre() + ", " + cantFallas + " Fallas.");
        }
    }
}