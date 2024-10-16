package com.utndds.heladerasApi.services;

import java.util.ArrayList;
import java.util.List;

import com.utndds.heladerasApi.DTOs.PuntoMapaDTO;
import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Heladera.Punto;

import org.springframework.stereotype.Service;

@Service
public class GoogleMapsService {
        private static volatile GoogleMapsService instancia;

        // evitar la instanciación directa
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

                List<Heladera> heladeras = new ArrayList<>();
                heladeras.add(new Heladera(new Punto(-34.6037, -58.3816, "Heladera Obelisco", "Buenos Aires"), 0,
                                null, null, true, false, null));
                heladeras.add(new Heladera(new Punto(-31.4201, -64.1888, "Heladera de Córdoba", "Córdoba"), 0,
                                null, null, true, false, null));
                heladeras.add(new Heladera(new Punto(-31.6333, -60.7011, "Heladera de Santa Fe", "Santa Fe"), 0,
                                null, null, false, false, null));
                heladeras.add(new Heladera(new Punto(-27.4693, -57.9963, "Heladera de Corrientes", "Corrientes"), 0,
                                null, null, true, false, null));
                heladeras.add(new Heladera(new Punto(-31.7359, -60.5238, "Heladera de Entre Ríos", "Entre Ríos"), 0,
                                null, null, false, false, null));

                for (Heladera heladera : heladeras) {
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
}