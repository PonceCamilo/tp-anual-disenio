package com.utndds.heladerasApi.services;

import java.util.ArrayList;
import java.util.List;
import com.utndds.heladerasApi.DTOs.PuntoMapaDTO;
import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Heladera.ManejadorTemperatura;
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


                /* TEST */

                List<Heladera> heladeras = new ArrayList<>();
                heladeras.add(new Heladera(ong, new Punto(-34.6037, -58.3816, "Heladera Obelisco", "Buenos Aires"), 0, new ManejadorTemperatura(), true, false, null));
                heladeras.add(new Heladera(ong, new Punto(-31.4201, -64.1888, "Heladera de Córdoba", "Córdoba"), 0, new ManejadorTemperatura(), true, false, null));
                heladeras.add(new Heladera(ong, new Punto(-31.6333, -60.7011, "Heladera de Santa Fe", "Santa Fe"), 0, new ManejadorTemperatura(), false, false, null));
                heladeras.add(new Heladera(ong, new Punto(-27.4693, -57.9963, "Heladera de Corrientes", "Corrientes"), 0, new ManejadorTemperatura(), true, false, null));
                heladeras.add(new Heladera(ong, new Punto(-31.7359, -60.5238, "Heladera de Entre Ríos", "Entre Ríos"), 0, new ManejadorTemperatura(), false, false, null));

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