package com.utndds.heladerasApi.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utndds.heladerasApi.DTOs.PuntoMapaDTO;
import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Heladera.Punto;

import com.utndds.heladerasApi.models.ONG.ONG;
import org.springframework.stereotype.Service;

@Service
public class GoogleMapsService {
    private static volatile GoogleMapsService instancia;

    // Constructor privado para evitar la instanciación directa
    private GoogleMapsService() {
    }

    // Método público para obtener la instancia única
    public static GoogleMapsService getInstance() {
        if (instancia == null) {
            synchronized (GoogleMapsService.class) {
                if (instancia == null) {
                    instancia = new GoogleMapsService();
                }
            }
        }
        return instancia;
    }

    public List<PuntoMapaDTO> obtenerUbicaciones() {
        List<PuntoMapaDTO> ubicaciones = new ArrayList<>();

        ONG ong = ONG.getInstance();

        List<Heladera> heladeras = ong.getHeladeras();

        test(heladeras);

        for(Heladera heladera : heladeras) {
            Punto punto = heladera.getPunto();
            String nombre = punto.getNombre();
            double latitud = punto.getLatitud();
            double longitud = punto.getLongitud();
            String direccion = punto.getDireccion();
            Boolean funcionando = heladera.isFuncionando();


            PuntoMapaDTO puntoMapa = new PuntoMapaDTO(nombre, latitud, longitud, direccion, funcionando);

            ubicaciones.add(puntoMapa);
        }

        return ubicaciones;
    }

    private void test(List<Heladera> heladeras) {
        heladeras.add(new Heladera(
                null, // ONG (asumido como null o una instancia si tienes)
                new Punto(-24.7825, -65.4232, "Heladera de Salta", "Salta"),
                0, // Capacidad (asumido como 0 o un valor adecuado si tienes)
                0.0, // minTemp (asumido como 0.0 o un valor adecuado si tienes)
                0.0, // maxTemp (asumido como 0.0 o un valor adecuado si tienes)
                false, // Funcionando (asumido como false o true si tienes esa información)
                false, // Abierta (asumido como false o true si tienes esa información)
                null, // fechaInicioFuncionamiento (asumido como null o una fecha adecuada si tienes)
                null
        ));

        heladeras.add(new Heladera(
                null,
                new Punto(-34.6037, -58.3816, "Heladera Obelisco", "Buenos Aires"),
                0,
                0.0,
                0.0,
                false,
                false,
                null,
                null
        ));

        heladeras.add(new Heladera(
                null,
                new Punto(-31.4201, -64.1888, "Heladera de Córdoba", "Córdoba"),
                0,
                0.0,
                0.0,
                true,
                false,
                null,
                null
        ));

        heladeras.add(new Heladera(
                null,
                new Punto(-32.8902, -68.8442, "Heladera de Mendoza", "Mendoza"),
                0,
                0.0,
                0.0,
                false,
                false,
                null,
                null
        ));

        heladeras.add(new Heladera(
                null,
                new Punto(-26.8083, -65.2176, "Heladera de Tucumán", "Tucumán"),
                0,
                0.0,
                0.0,
                false,
                false,
                null,
                null
        ));

        heladeras.add(new Heladera(
                null,
                new Punto(-23.8512, -65.4173, "Heladera de Jujuy", "Jujuy"),
                0,
                0.0,
                0.0,
                false,
                false,
                null,
                null
        ));

        heladeras.add(new Heladera(
                null,
                new Punto(-31.7359, -60.5238, "Heladera de Entre Ríos", "Entre Ríos"),
                0,
                0.0,
                0.0,
                true,
                true,
                null,
                null
        ));

        heladeras.add(new Heladera(
                null,
                new Punto(-31.6333, -60.7011, "Heladera de Santa Fe", "Santa Fe"),
                0,
                0.0,
                0.0,
                false,
                false,
                null,
                null
        ));

        heladeras.add(new Heladera(
                null,
                new Punto(-27.3625, -55.8963, "Heladera de Misiones", "Misiones"),
                0,
                0.0,
                0.0,
                false,
                false,
                null,
                null
        ));

        heladeras.add(new Heladera(
                null,
                new Punto(-36.6167, -64.2833, "Heladera de La Pampa", "La Pampa"),
                0,
                0.0,
                0.0,
                false,
                false,
                null,
                null
        ));

        heladeras.add(new Heladera(
                null,
                new Punto(-38.9516, -68.0591, "Heladera de Neuquén", "Neuquén"),
                0,
                0.0,
                0.0,
                false,
                false,
                null,
                null
        ));

        heladeras.add(new Heladera(
                null,
                new Punto(-41.1335, -71.3103, "Heladera de Río Negro", "Río Negro"),
                0,
                0.0,
                0.0,
                true,
                false,
                null,
                null
        ));

        heladeras.add(new Heladera(
                null,
                new Punto(-43.3000, -65.1022, "Heladera de Chubut", "Chubut"),
                0,
                0.0,
                0.0,
                false,
                false,
                null,
                null
        ));

        heladeras.add(new Heladera(
                null,
                new Punto(-51.6226, -69.2181, "Heladera de Santa Cruz", "Santa Cruz"),
                0,
                0.0,
                0.0,
                false,
                false,
                null,
                null
        ));

        heladeras.add(new Heladera(
                null,
                new Punto(-54.8019, -68.3030, "Heladera de Tierra del Fuego", "Tierra del Fuego"),
                0,
                0.0,
                0.0,
                false,
                false,
                null,
                null
        ));

        heladeras.add(new Heladera(
                null,
                new Punto(-27.4513, -58.9866, "Heladera de Chaco", "Chaco"),
                0,
                0.0,
                0.0,
                false,
                false,
                null,
                null
        ));

        heladeras.add(new Heladera(
                null,
                new Punto(-29.4131, -66.8565, "Heladera de La Rioja", "La Rioja"),
                0,
                0.0,
                0.0,
                true,
                false,
                null,
                null
        ));

        heladeras.add(new Heladera(
                null,
                new Punto(-27.7951, -64.2615, "Heladera de Santiago del Estero", "Santiago del Estero"),
                0,
                0.0,
                0.0,
                false,
                false,
                null,
                null
        ));

        heladeras.add(new Heladera(
                null,
                new Punto(-26.1775, -58.1781, "Heladera de Formosa", "Formosa"),
                0,
                0.0,
                0.0,
                false,
                false,
                null,
                null
        ));

        heladeras.add(new Heladera(
                null,
                new Punto(-31.5365, -68.5389, "Heladera de San Juan", "San Juan"),
                0,
                0.0,
                0.0,
                false,
                false,
                null,
                null
        ));

        heladeras.add(new Heladera(
                null,
                new Punto(-33.2950, -66.3356, "Heladera de San Luis", "San Luis"),
                0,
                0.0,
                0.0,
                false,
                false,
                null,
                null
        ));

        heladeras.add(new Heladera(
                null,
                new Punto(-28.4696, -65.7852, "Heladera de Catamarca", "Catamarca"),
                0,
                0.0,
                0.0,
                false,
                false,
                null,
                null
        ));

        heladeras.add(new Heladera(
                null,
                new Punto(-27.4693, -57.9963, "Heladera de Corrientes", "Corrientes"),
                0,
                0.0,
                0.0,
                false,
                false,
                null,
                null
        ));
    }
}
