package com.utndds.heladerasApi.services.GeneradorReportes.Reportes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.utndds.heladerasApi.models.Colaboraciones.Colaboracion;
import com.utndds.heladerasApi.models.Colaboraciones.DistribucionViandas;
import com.utndds.heladerasApi.models.ONG.ONG;
import com.utndds.heladerasApi.models.Rol.Colaborador;

@Service
public class ViandasMovidasPorColaborador extends Reporte {

    public void generarReporte() {
        this.mostrarViandasDistribuidasPorColaborador();
    }

    private void mostrarViandasDistribuidasPorColaborador() {
        List<Colaborador> colaboradores = ONG.getInstance().getColaboradores();
        for (Colaborador colaborador : colaboradores) {
            int cantViandasDistribuidas = this.cantidadViandasDistribuidas(colaborador.getColaboraciones());
            System.out.println("Colaborador: " + colaborador.getPersona().getNombre() + ", " + cantViandasDistribuidas
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
