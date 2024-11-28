package com.utndds.heladerasApi.repositories.TarjetasRepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utndds.heladerasApi.models.Tarjetas.Tarjeta;
import java.util.Optional;

public interface TarjetaRepository extends JpaRepository<Tarjeta, Long> {
    @SuppressWarnings("null")
    Optional<Tarjeta> findById(Long id);
}