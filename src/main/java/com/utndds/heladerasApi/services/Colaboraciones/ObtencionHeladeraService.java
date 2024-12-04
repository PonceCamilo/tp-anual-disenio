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

    public void registrarObtencionHeladera(String direccion,Double lat,Double lng, String colaboradorUUID) {

        // Buscar al colaborador
        Colaborador colaborador = colaboradorRepository.findByUUID(colaboradorUUID)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Colaborador no encontrado con uuid " + colaboradorUUID));
        // Buscar o crear un nuevo Punto basado en la dirección
        Punto punto = puntoRepository.findByDireccion(direccion)
                .orElseGet(() -> {
                    Punto nuevoPunto = new Punto();
                    nuevoPunto.setDireccion(direccion);
                    nuevoPunto.setLatitud(lat); // Placeholder para latitud
                    nuevoPunto.setLongitud(lng); // Placeholder para longitud
                    return puntoRepository.save(nuevoPunto);
                    
                });
                

        // Crear una nueva Heladera asociada al Punto
        Heladera heladera = new Heladera();
        heladera.setCantViandas(0);
        heladera.setCapacidad(20);
        heladera.setFuncionando(true);
        heladera.setTempMax(16.0);
        heladera.setTempMin(2.0);
        heladera.setFechaInicioFuncionamiento(java.time.LocalDate.now());
        heladera.setPunto(punto);

        // Guardar la Heladera
        heladeraRepository.save(heladera);

        // Guardar el registro de la donación ObtencionHeladera
        ObtencionHeladera obtencionHeladera = new ObtencionHeladera(colaborador, heladera);
        obtencionHeladeraRepository.save(obtencionHeladera);
    }
}