package com.utndds.heladerasApi.services.GeneradorReportes.Reportes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.ONG.ONG;

@Service
public class FallasPorHeladera extends Reporte {

    public void generarReporte() {
        this.mostrarFallasPorHeladera();
    }

    private void mostrarFallasPorHeladera() {

        List<Heladera> heladeras = ONG.getInstance().getHeladeras();
        for (Heladera heladera : heladeras) {
            int cantFallas = heladera.cantFallas();
            System.out.println("Heladera: " + heladera.getPunto().getNombre() + ", " + cantFallas + " Fallas.");
        }
    }
}
