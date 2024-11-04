package com.utndds.heladerasApi.services.Colaboraciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utndds.heladerasApi.DTOs.OfertaDTO;
import com.utndds.heladerasApi.models.Colaboraciones.Ofertas.Oferta;
import com.utndds.heladerasApi.models.Rol.Colaborador;
import com.utndds.heladerasApi.repositories.ColaboradorRepository;
import com.utndds.heladerasApi.repositories.ColaboracionesRepositories.OfertaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class OfertaService {

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @Autowired
    private OfertaRepository ofertaRepository;

    public void registrarOferta(OfertaDTO ofertaDTO, String colaboradorUUID) {

        // Buscar al colaborador (empresa) que ofrece la oferta
        Colaborador colaborador = colaboradorRepository.findByUUID(colaboradorUUID)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Colaborador no encontrado con uuid " + colaboradorUUID));

        // Crear una nueva oferta usando los datos del DTO
        Oferta oferta = new Oferta();
        oferta.setRubro(ofertaDTO.getRubro());
        oferta.setNombre(ofertaDTO.getNombre());
        oferta.setCantidadPuntosNec(ofertaDTO.getCantidadPuntosNec());
        oferta.setImagen(ofertaDTO.getImagen());
        oferta.setColaborador(colaborador);

        // Guardar la oferta en la base de datos
        ofertaRepository.save(oferta);
    }
}