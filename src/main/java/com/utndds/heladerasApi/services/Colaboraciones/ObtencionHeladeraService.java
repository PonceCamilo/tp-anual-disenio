package com.utndds.heladerasApi.services.Colaboraciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.utndds.heladerasApi.models.Colaboraciones.ObtencionHeladera;
import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Heladera.Punto;
import com.utndds.heladerasApi.models.Rol.Colaborador;
import com.utndds.heladerasApi.repositories.ColaboradorRepository;
import com.utndds.heladerasApi.repositories.HeladeraRepository;
import com.utndds.heladerasApi.repositories.PuntoRepository;
import com.utndds.heladerasApi.repositories.ColaboracionesRepositories.ObtencionHeladeraRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ObtencionHeladeraService {

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @Autowired
    private HeladeraRepository heladeraRepository;

    @Autowired
    private PuntoRepository puntoRepository;

    @Autowired
    private ObtencionHeladeraRepository obtencionHeladeraRepository;

    public void registrarObtencionHeladera(String direccion, String colaboradorUUID) {

        // Buscar al colaborador
        Colaborador colaborador = colaboradorRepository.findByUUID(colaboradorUUID)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Colaborador no encontrado con uuid " + colaboradorUUID));

        // Buscar o crear un nuevo Punto basado en la dirección
        Punto punto = puntoRepository.findByDireccion(direccion)
                .orElseGet(() -> {
                    Punto nuevoPunto = new Punto();
                    nuevoPunto.setDireccion(direccion);
                    // Asignar valores predeterminados o calcular las coordenadas latitud/longitud
                    // si es necesario
                    nuevoPunto.setLatitud(0.0); // Placeholder para latitud
                    nuevoPunto.setLongitud(0.0); // Placeholder para longitud
                    return puntoRepository.save(nuevoPunto);
                });

        // Crear una nueva Heladera asociada al Punto
        Heladera heladera = new Heladera();
        heladera.setPunto(punto);

        // Guardar la Heladera
        heladeraRepository.save(heladera);

        // Guardar el registro de la donación ObtencionHeladera
        ObtencionHeladera obtencionHeladera = new ObtencionHeladera(colaborador, heladera);
        obtencionHeladeraRepository.save(obtencionHeladera);
    }
}