package com.utndds.heladerasApi.services;

import com.utndds.heladerasApi.models.Enums.MotivoApertura;
import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Rol.Colaborador;
import com.utndds.heladerasApi.models.Rol.PersonaVulnerable;

import org.springframework.stereotype.Service;
import com.utndds.heladerasApi.models.Solicitudes.SolicitudApertura;
import com.utndds.heladerasApi.models.Tarjetas.Apertura;
import com.utndds.heladerasApi.models.Tarjetas.Tarjeta;
import com.utndds.heladerasApi.models.Tarjetas.TarjetaPersVuln;
import com.utndds.heladerasApi.repositories.AperturaRepository;
import com.utndds.heladerasApi.repositories.ColaboradorRepository;
import com.utndds.heladerasApi.repositories.HeladeraRepository;
import com.utndds.heladerasApi.repositories.SolicitudAperturaRepository;
import com.utndds.heladerasApi.repositories.TarjetasRepositories.TarjetaColaboradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

import java.time.LocalDateTime;

@Service
public class AperturaService {

        @Autowired
        private SolicitudAperturaRepository solicitudAperturaRepository;

        @Autowired
        private AperturaRepository aperturaRepository;

        @Autowired
        private HeladeraRepository heladeraRepository;
        @Autowired
        private ColaboradorRepository colaboradorRepository;

        @Autowired
        private TarjetaColaboradorRepository tarjetaColaboradorRepository;

        public SolicitudApertura crearSolicitud(Long colaboradorId, Long heladeraId, MotivoApertura motivo) {
                // Buscar colaborador y heladera por ID
                Colaborador colaborador = colaboradorRepository.findById(colaboradorId)
                                .orElseThrow(() -> new RuntimeException("Colaborador no encontrado"));
                Heladera heladera = heladeraRepository.findById(heladeraId)
                                .orElseThrow(() -> new RuntimeException("Heladera no encontrada"));

                // Crear nueva solicitud de apertura
                SolicitudApertura solicitud = new SolicitudApertura(colaborador, heladera, motivo);
                return solicitudAperturaRepository.save(solicitud);
        }

        // Método para registrar la apertura de la heladera
        public String registrarApertura(Long idHeladera, Long idTarjeta) {
                // Buscar la heladera por su ID
                Heladera heladera = heladeraRepository.findById(idHeladera)
                                .orElseThrow(() -> new RuntimeException("Heladera no encontrada"));

                // Buscar la tarjeta por su ID
                Tarjeta tarjeta = tarjetaColaboradorRepository.findById(idTarjeta)
                                .orElseThrow(() -> new RuntimeException("Tarjeta no encontrada"));

                // Verificar el tipo de dueño de la tarjeta y realizar la acción correspondiente
                if (tarjeta.getDueño() instanceof Colaborador) {
                        return manejarAperturaColaborador(heladera, tarjeta);
                } else if (tarjeta.getDueño() instanceof PersonaVulnerable) {
                        return manejarAperturaPersonaVulnerable(heladera, tarjeta);
                } else {
                        return "Tipo de tarjeta no válido para abrir la heladera.";
                }
        }

        // Método privado para manejar la apertura cuando es un colaborador
        public String manejarAperturaColaborador(Heladera heladera, Tarjeta tarjeta) {
                // Obtener el colaborador a partir de la tarjeta
                Colaborador colaborador = (Colaborador) tarjeta.getDueño();

                // Verificar si existe una solicitud válida en las últimas 3 horas
                LocalDateTime tiempoLimite = LocalDateTime.now().minusHours(3);
                Optional<SolicitudApertura> solicitudOpt = solicitudAperturaRepository
                                .findFirstByColaboradorAndHeladeraAndFechaHoraBefore(
                                                colaborador,
                                                heladera,
                                                tiempoLimite);

                if (!solicitudOpt.isPresent()) {
                        return "No existe una solicitud válida para abrir la heladera.";
                }

                heladera.agregarVianda(); // Aumenta el inventario de viandas
                heladeraRepository.save(heladera);

                // Registrar la apertura en la base de datos
                Apertura nuevaApertura = new Apertura(heladera, tarjeta, null);
                aperturaRepository.save(nuevaApertura);

                return "El colaborador ha abierto la heladera y donado una vianda.";
        }

        // Método privado para manejar la apertura cuando es una persona vulnerable
        public String manejarAperturaPersonaVulnerable(Heladera heladera, Tarjeta tarjeta) {

                TarjetaPersVuln tarjetaPV = (TarjetaPersVuln) tarjeta;

                // Verificar si la tarjeta puede usarse
                if (!tarjetaPV.puedeUsarse(heladera)) {
                        return "La tarjeta de la persona vulnerable no puede usarse para abrir la heladera.";
                }

                // Verificar si hay viandas disponibles
                if (heladera.getCantViandas() <= 0) {
                        return "No hay viandas disponibles para extraer.";
                }

                // Extraer una vianda
                heladera.extraerVianda();
                heladeraRepository.save(heladera);

                // Registrar la apertura en la base de datos
                Apertura nuevaApertura = new Apertura(heladera, tarjetaPV, MotivoApertura.CONSUMICION);
                aperturaRepository.save(nuevaApertura);

                return "La persona vulnerable ha extraído una vianda con éxito.";
        }
}