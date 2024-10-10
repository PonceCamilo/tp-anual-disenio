package com.utndds.heladerasApi.services;

import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.repositories.HeladeraRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HeladeraService {

    @Autowired
    private HeladeraRepository heladeraRepository;

    // Alta: Crear una nueva heladera
    public Heladera crearHeladera(Heladera heladera) {
        return heladeraRepository.save(heladera); // Guardar la nueva heladera en la base de datos
    }

    // Baja: Eliminar una heladera por ID
    public void eliminarHeladera(Long id) {
        heladeraRepository.deleteById(id); // Eliminar la heladera por su ID
    }

    // Modificación: Actualizar una heladera
    public Heladera actualizarHeladera(Heladera heladera) {
        return heladeraRepository.save(heladera); // Si ya existe, se actualiza; si no, se crea
    }

}
