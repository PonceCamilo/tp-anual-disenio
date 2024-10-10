package com.utndds.heladerasApi.services;

import com.utndds.heladerasApi.controllers.DTOs.VisitaTecnicoDTO;
import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Heladera.Incidentes.VisitaTecnico;
import com.utndds.heladerasApi.models.Heladera.Incidentes.Incidente.Alerta;
import com.utndds.heladerasApi.models.Heladera.Incidentes.Incidente.Incidente;
import com.utndds.heladerasApi.models.Rol.Tecnico.Tecnico;
import com.utndds.heladerasApi.repositories.HeladeraRepository;
import com.utndds.heladerasApi.repositories.TecnicoRepository;
import com.utndds.heladerasApi.repositories.VisitaTecnicoRepository;
import com.utndds.heladerasApi.repositories.Incidentes.IncidenteRepository;

import jakarta.persistence.EntityNotFoundException;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncidenteService {

    @Autowired
    private TecnicoRepository tecnicoRepository; // Asumiendo que tienes un repositorio para los técnicos

    @Autowired
    private IncidenteRepository incidenteRepository;

    @Autowired
    private VisitaTecnicoRepository visitaTecnicoRepository; // Repositorio para registrar visitas

    @Autowired
    private HeladeraRepository heladeraRepository; // Repositorio para registrar visitas

    public void reportarIncidente(Incidente incidente) {
        incidenteRepository.save(incidente);
        this.notificarTecnicoCercano(incidente);
    }

    public void notificarTecnicoCercano(Incidente incidente) {
        Tecnico tecnico = this.obtenerTecnicoMasCercano(incidente.getHeladera());
        if (tecnico != null) {
            String mensaje = "NECESITAMOS TUS SERVICIOS EN LA DIRECCION: "
                    + incidente.getHeladera().getPunto().getDireccion();
            tecnico.getPersona().notificar(mensaje);

            // Relacionamos el incidente con el técnico
            VisitaTecnico nuevaVisita = new VisitaTecnico(LocalDate.now(), tecnico, incidente, "Pendiente", null,
                    false);
            visitaTecnicoRepository.save(nuevaVisita);
        }
    }

    private Tecnico obtenerTecnicoMasCercano(Heladera heladera) {
        return tecnicoRepository.findTecnicoCercano(heladera.getPunto().getLatitud(),
                heladera.getPunto().getLongitud());
    }

    public void registrarVisita(Long tecnicoId, VisitaTecnicoDTO visitaDTO) {

        // Buscar el técnico por ID
        Tecnico tecnico = tecnicoRepository.findById(tecnicoId)
                .orElseThrow(() -> new EntityNotFoundException("Técnico no encontrado con id " + tecnicoId));

        // Buscar el incidente por ID
        Incidente incidente = incidenteRepository.findById(visitaDTO.getIncidenteId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Incidente no encontrado con id " + visitaDTO.getIncidenteId()));

        // Buscar la heladera asociada al incidente
        Heladera heladera = incidente.getHeladera();

        // Crear una nueva instancia de VisitaTecnico
        VisitaTecnico visitaTecnico = new VisitaTecnico();
        visitaTecnico.setTecnico(tecnico);
        visitaTecnico.setIncidente(incidente);
        visitaTecnico.setFecha(visitaDTO.getFechaVisita());
        visitaTecnico.setDescripcion(visitaDTO.getComentario());
        visitaTecnico.setFoto(visitaDTO.getFoto());
        visitaTecnicoRepository.save(visitaTecnico); // Guardar la visita

        heladera.setFuncionando(visitaDTO.isSolucionado());
    }

    public void generarAlerta(Long heladeraId, String tipoAlerta) {
        // Buscar la heladera por ID
        Heladera heladera = heladeraRepository.findById(heladeraId)
                .orElseThrow(() -> new RuntimeException("Heladera no encontrada con ID: " + heladeraId));

        // Crear la alerta
        Alerta alerta = new Alerta(heladera, tipoAlerta);

        // Guardar la alerta en la base de datos
        incidenteRepository.save(alerta);

        System.out.println("Alerta generada para la heladera ID: " + heladeraId + " de tipo: " + tipoAlerta);
    }

}