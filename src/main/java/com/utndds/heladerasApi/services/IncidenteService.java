package com.utndds.heladerasApi.services;

import com.utndds.heladerasApi.DTOs.FallaTecnicaDTO;
import com.utndds.heladerasApi.DTOs.VisitaTecnicoDTO;
import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Heladera.Incidentes.VisitaTecnico;
import com.utndds.heladerasApi.models.Heladera.Incidentes.Incidente.Alerta;
import com.utndds.heladerasApi.models.Heladera.Incidentes.Incidente.FallaTecnica;
import com.utndds.heladerasApi.models.Heladera.Incidentes.Incidente.Incidente;
import com.utndds.heladerasApi.models.Rol.Colaborador;
import com.utndds.heladerasApi.models.Rol.Tecnico.Tecnico;
import com.utndds.heladerasApi.repositories.ColaboradorRepository;
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
    @Autowired
    private ColaboradorRepository colaboradorRepository; // Repositorio para registrar visitas

    public void reportarIncidente(Incidente incidente) {
        incidenteRepository.save(incidente);
    }

    public void reportarFallaTecnica(Long colaboradorId, FallaTecnicaDTO fallaTecnicaDTO) {
        Colaborador colaborador = colaboradorRepository.findById(colaboradorId)
                .orElseThrow(() -> new RuntimeException("Colaborador no encontrado con ID: " + colaboradorId));

        // Find the heladera by ID and handle if not found
        Heladera heladera = heladeraRepository.findById(fallaTecnicaDTO.getHeladeraId())
                .orElseThrow(() -> new RuntimeException(
                        "Heladera no encontrada con ID: " + fallaTecnicaDTO.getHeladeraId()));

        // Create a new instance of FallaTecnica with the DTO data
        FallaTecnica fallaTecnica = new FallaTecnica();
        fallaTecnica.setColaborador(colaborador);
        fallaTecnica.setHeladera(heladera); // Set the Heladera object
        fallaTecnica.setDescripcion(fallaTecnicaDTO.getDescripcion()); // Set the description
        fallaTecnica.setFoto(fallaTecnicaDTO.getFoto()); // Set the photo

        // Save the technical failure in the incident repository
        incidenteRepository.save(fallaTecnica);

        // Notify the nearest technician about the failure
        this.notificarTecnicoCercano(fallaTecnica);
    }

    public void notificarTecnicoCercano(FallaTecnica fallaTecnica) {
        Tecnico tecnico = this.obtenerTecnicoMasCercano(fallaTecnica.getHeladera());
        if (tecnico != null) {
            String mensaje = "NECESITAMOS TUS SERVICIOS EN LA DIRECCION: "
                    + fallaTecnica.getHeladera().getPunto().getDireccion();
            tecnico.getPersona().notificar(mensaje);

            // Relacionamos el incidente con el técnico
            VisitaTecnico nuevaVisita = new VisitaTecnico(LocalDate.now(), tecnico, fallaTecnica, "Pendiente", null,
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