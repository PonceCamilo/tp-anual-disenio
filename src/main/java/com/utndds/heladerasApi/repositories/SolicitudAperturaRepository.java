package com.utndds.heladerasApi.repositories;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Rol.Colaborador;
import com.utndds.heladerasApi.models.Solicitudes.SolicitudApertura;
import java.util.Optional;

@Repository
public interface SolicitudAperturaRepository extends JpaRepository<SolicitudApertura, Long> {

        Optional<SolicitudApertura> findFirstByColaboradorAndHeladeraAndFechaHoraAfter(Colaborador colaborador,
                        Heladera heladera, LocalDateTime tiempoLimite);

        Optional<SolicitudApertura> findFirstByColaboradorAndHeladeraAndFechaHoraBefore(Colaborador colaborador,
                Heladera heladera, LocalDateTime tiempoLimite);
}