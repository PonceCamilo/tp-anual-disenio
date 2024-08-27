package com.utndds.heladerasApi.services;

import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.ONG.ONG;
import com.utndds.heladerasApi.repositories.HeladeraRepository;
import com.utndds.heladerasApi.repositories.ONGRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HeladeraService {

    @Autowired
    private HeladeraRepository heladeraRepository;

    @Autowired
    private ONGRepository ongRepository;

    @Transactional
    public Heladera agregarHeladera(Heladera heladera) {
        // Buscar ONG por nombre
        ONG existingOng = ongRepository.findByNombre(heladera.getOng().getNombre());

        if (existingOng != null) {
            // Si existe, usar la ONG encontrada
            heladera.setOng(existingOng);
        } else {
            // Si no existe, la ONG se creará con los datos proporcionados
            ongRepository.save(heladera.getOng());
        }

        // Guardar la heladera con la ONG existente o nueva
        return heladeraRepository.save(heladera);
    }
}
