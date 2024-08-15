package com.utndds.heladerasApi.services.CargaCSV;

import java.util.ArrayList;
import java.util.List;

import com.utndds.heladerasApi.models.Colaboraciones.Colaboracion;
import com.utndds.heladerasApi.models.Colaboraciones.DistribucionViandas;
import com.utndds.heladerasApi.models.Colaboraciones.DonacionDinero;
import com.utndds.heladerasApi.models.Colaboraciones.DonacionVianda;
import com.utndds.heladerasApi.models.Colaboraciones.RegistroPersonaVulnerable;
import com.utndds.heladerasApi.models.Heladera.Vianda;
import com.utndds.heladerasApi.models.Rol.Colaborador;
import com.utndds.heladerasApi.models.Tarjetas.TarjetaPersVuln.TarjetaPersVuln;

public class ColaboracionFactory {
    public List<Colaboracion> crearColaboracion(String[] registro, Colaborador colaborador) {
        String tipoColaboracion = registro[5];
        List<Colaboracion> colaboraciones = new ArrayList<>();

        switch (tipoColaboracion) {
            case "DINERO":
                double monto = Double.parseDouble(registro[6]);
                DonacionDinero dinero = new DonacionDinero(colaborador, monto, 0);
                colaboraciones.add(dinero);
                break;

            case "DONACION_VIANDAS":
                int cantidadViandas = Integer.parseInt(registro[6]);
                List<Vianda> viandasDonadas = new ArrayList<>();

                for (int i = 0; i < cantidadViandas; i++) {
                    Vianda viandaDonada = new Vianda(null, null, 0, 0, true, null);
                    viandasDonadas.add(viandaDonada);
                }
                DonacionVianda donacionVianda = new DonacionVianda(colaborador, viandasDonadas,
                        null, true);
                colaboraciones.add(donacionVianda);
                break;

            case "REDISTRIBUCION_VIANDAS":
                int cantidadMovida = Integer.parseInt(registro[6]);
                DistribucionViandas redistribucionVianda = new DistribucionViandas(colaborador, null, null,
                        cantidadMovida, null);
                colaboraciones.add(redistribucionVianda);
                break;

            case "ENTREGA_TARJETAS":
                int cantidadTarjetas = Integer.parseInt(registro[6]);
                for (int t = 0; t < cantidadTarjetas; t++) {
                    TarjetaPersVuln tarj = new TarjetaPersVuln(null);
                    RegistroPersonaVulnerable registroPV = new RegistroPersonaVulnerable(colaborador, null, tarj);
                    colaboraciones.add(registroPV);
                }
                break;
        }

        return colaboraciones;
    }

}