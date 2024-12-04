package com.utndds.heladerasApi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.utndds.heladerasApi.models.Heladera.Punto;

public interface PuntoRepository extends JpaRepository<Punto, Long> {
    Optional<Punto> findByDireccion(String direccion);
}