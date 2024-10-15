package com.utndds.heladerasApi.services.Canjes;

import com.utndds.heladerasApi.models.Colaboraciones.Ofertas.Canje;
import com.utndds.heladerasApi.models.Colaboraciones.Ofertas.Oferta;
import com.utndds.heladerasApi.models.Rol.Colaborador;
import com.utndds.heladerasApi.repositories.CanjeRepository;
import com.utndds.heladerasApi.repositories.ColaboradorRepository;
import com.utndds.heladerasApi.repositories.ColaboracionesRepositories.OfertaRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CanjesService {

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @Autowired
    private OfertaRepository ofertaRepository;

    @Autowired
    private CalculadoraPuntosService calculadoraPuntosService;

    @Autowired
    private CanjeRepository canjeRepository; // Agregamos el repositorio para Canje

    public boolean canjearOferta(Long colaboradorId, Long ofertaId) {

        // Buscar al colaborador
        Colaborador colaborador = colaboradorRepository.findById(colaboradorId)
                .orElseThrow(() -> new EntityNotFoundException("Colaborador no encontrado con id " + colaboradorId));

        // Buscar la oferta
        Oferta oferta = ofertaRepository.findById(ofertaId)
                .orElseThrow(() -> new EntityNotFoundException("Oferta no encontrada con id " + ofertaId));

        // Calcular los puntos disponibles del colaborador
        double puntosDisponibles = calculadoraPuntosService.calcularPuntos(colaboradorId);

        // Verificar si el colaborador tiene puntos suficientes para canjear la oferta
        if (puntosDisponibles >= oferta.getCantidadPuntosNec()) {
            // Crear un nuevo objeto Canje
            Canje canje = new Canje(colaborador, oferta, oferta.getCantidadPuntosNec());

            // Guardar el canje en la base de datos
            canjeRepository.save(canje); // Guardamos el canje

            // Guardar el colaborador actualizado
            colaboradorRepository.save(colaborador);

            return true; // Canje exitoso
        } else {
            // El colaborador no tiene suficientes puntos
            return false; // Canje fallido
        }
    }
}