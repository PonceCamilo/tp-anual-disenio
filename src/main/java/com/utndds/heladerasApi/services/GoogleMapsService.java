package com.utndds.heladerasApi.services;

import java.util.ArrayList;
import java.util.List;

import com.utndds.heladerasApi.DTOs.PuntoMapaDTO;
import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Heladera.Punto;
import com.utndds.heladerasApi.repositories.HeladeraRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoogleMapsService {

        @Autowired
        private HeladeraRepository heladeraRepository;

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

                // Obtiene todas las heladeras desde el repositorio
                List<Heladera> heladeras = heladeraRepository.findAll();

                // Recorre cada heladera y crea un DTO con los datos de su ubicación
                for (Heladera heladera : heladeras) {
                        Punto punto = heladera.getPunto();
                        String nombre = punto.getNombre();
                        double latitud = punto.getLatitud();
                        double longitud = punto.getLongitud();
                        String direccion = punto.getDireccion();
                        Boolean funcionando = heladera.isFuncionando();

                        // Crea el DTO con la información necesaria
                        PuntoMapaDTO puntoMapa = new PuntoMapaDTO(nombre, latitud, longitud, direccion, funcionando);
                        ubicaciones.add(puntoMapa);
                }

                return ubicaciones;
        }
}