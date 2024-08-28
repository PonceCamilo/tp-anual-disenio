package com.utndds.heladerasApi.services;

import com.utndds.heladerasApi.models.Colaboraciones.Colaboracion;
import com.utndds.heladerasApi.models.Colaboraciones.DistribucionViandas;
import com.utndds.heladerasApi.models.Colaboraciones.DonacionDinero;
import com.utndds.heladerasApi.models.Colaboraciones.DonacionVianda;
import com.utndds.heladerasApi.models.Colaboraciones.ObtencionHeladera;
import com.utndds.heladerasApi.models.Colaboraciones.RegistroPersonaVulnerable;
import com.utndds.heladerasApi.models.Rol.Colaborador;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CalculadoraPuntosService {

    public double calcularPuntos(Colaborador colaborador) {
        List<Colaboracion> colaboraciones = colaborador.getColaboraciones();
        double puntosTotales = 0;
        puntosTotales += this.pesosDonados(colaboraciones);
        puntosTotales += this.viandasDistribuidas(colaboraciones);
        puntosTotales += this.viandasDonadas(colaboraciones);
        puntosTotales += this.tarjetasRepartidas(colaboraciones);
        puntosTotales += this.obtencionHeladeras(colaboraciones);

        return puntosTotales;
    }

    private double pesosDonados(List<Colaboracion> colaboraciones) {
        int montoTotal = 0;

        for (Colaboracion colaboracion : colaboraciones) {
            if (colaboracion instanceof DonacionDinero) {
                DonacionDinero donacionDinero = (DonacionDinero) colaboracion;
                montoTotal += donacionDinero.getMonto();
            }
        }
        return montoTotal * 0.5;
    }

    private double viandasDistribuidas(List<Colaboracion> colaboraciones) {
        int contador = 0;

        for (Colaboracion colaboracion : colaboraciones) {
            if (colaboracion instanceof DistribucionViandas) {
                contador++;
            }
        }
        return contador * 1;

    }

    private double viandasDonadas(List<Colaboracion> colaboraciones) {
        int contador = 0;

        for (Colaboracion colaboracion : colaboraciones) {
            if (colaboracion instanceof DonacionVianda) {
                contador += 1;
            }
        }
        return contador * 1.5;

    }

    private double tarjetasRepartidas(List<Colaboracion> colaboraciones) {
        int contador = 0;

        for (Colaboracion colaboracion : colaboraciones) {
            if (colaboracion instanceof RegistroPersonaVulnerable) {
                contador++;
            }
        }
        return contador * 2;

    }

    private double obtencionHeladeras(List<Colaboracion> colaboraciones) {
        int contador = 0;
        int totalMeses = 0;

        for (Colaboracion colaboracion : colaboraciones) {
            if (colaboracion instanceof ObtencionHeladera) {
                ObtencionHeladera obtencion = (ObtencionHeladera) colaboracion;
                contador++;
                totalMeses += obtencion.getHeladera().cantMesesActiva();
            }
        }
        return contador * totalMeses * 5;

    }

}
