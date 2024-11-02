package com.utndds.heladerasApi.repositories;

import com.utndds.heladerasApi.models.Rol.Tecnico.Tecnico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TecnicoRepository extends JpaRepository<Tecnico, Long> {

        @Query("SELECT t FROM Tecnico t WHERE "
                        + "(6371 * acos(cos(radians(:latitudHeladera)) * cos(radians(t.areaCobertura.latitudCentro)) "
                        + "* cos(radians(t.areaCobertura.longitudCentro) - radians(:longitudHeladera)) + sin(radians(:latitudHeladera)) "
                        + "* sin(radians(t.areaCobertura.latitudCentro)))) <= t.areaCobertura.radio "
                        + "ORDER BY (6371 * acos(cos(radians(:latitudHeladera)) * cos(radians(t.areaCobertura.latitudCentro)) "
                        + "* cos(radians(t.areaCobertura.longitudCentro) - radians(:longitudHeladera)) + sin(radians(:latitudHeladera)) "
                        + "* sin(radians(t.areaCobertura.latitudCentro)))) ASC")
        Tecnico findTecnicoCercano(@Param("latitudHeladera") double latitudHeladera,
                        @Param("longitudHeladera") double longitudHeladera);

        Optional <Tecnico> findByUUID(String UUID);
}