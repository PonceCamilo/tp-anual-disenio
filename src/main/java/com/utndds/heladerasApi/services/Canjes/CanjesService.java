package com.utndds.heladerasApi.services.Canjes;

import com.utndds.heladerasApi.models.Colaboraciones.Ofertas.Canje;
import com.utndds.heladerasApi.models.Colaboraciones.Ofertas.Oferta;
import com.utndds.heladerasApi.models.Rol.Colaborador;
import com.utndds.heladerasApi.repositories.CanjeRepository;
import com.utndds.heladerasApi.repositories.ColaboradorRepository;
import com.utndds.heladerasApi.repositories.ColaboracionesRepositories.OfertaRepository;

import jakarta.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public boolean canjearOferta(String colaboradorUUID, Long ofertaId) {

        // Buscar al colaborador
        Colaborador colaborador = colaboradorRepository.findByUUID(colaboradorUUID)
                .orElseThrow(
                        () -> new EntityNotFoundException("Colaborador no encontrado con uuid " + colaboradorUUID));

        // Buscar la oferta
        Oferta oferta = ofertaRepository.findById(ofertaId)
                .orElseThrow(() -> new EntityNotFoundException("Oferta no encontrada con id " + ofertaId));

        // Calcular los puntos disponibles del colaborador
        double puntosDisponibles = calculadoraPuntosService.calcularPuntos(colaboradorUUID);

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
    public List<Map<String, Object>> ofertasDisponibles() {
    // Obtener todas las ofertas
    Iterable<Oferta> ofertas = ofertaRepository.findAll();

    // Crear una lista para almacenar las ofertas en formato JSON
    List<Map<String, Object>> ofertasJson = new ArrayList<>();

    // Iterar sobre las ofertas
    for (Oferta oferta : ofertas) {
        Map<String, Object> ofertaMap = new HashMap<>();
        ofertaMap.put("id", oferta.getId());
        ofertaMap.put("nombre", oferta.getNombre());
        ofertaMap.put("rubro", oferta.getRubro());
        ofertaMap.put("cantidadPuntosNecesarios", oferta.getCantidadPuntosNec());
        ofertaMap.put("imagen", oferta.getImagen());
        ofertasJson.add(ofertaMap);
    }

    return ofertasJson;
}
}