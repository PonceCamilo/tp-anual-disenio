package com.utndds.heladerasApi.repositories.TarjetasRepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utndds.heladerasApi.models.Tarjetas.Tarjeta;

public interface TarjetaRepository extends JpaRepository<Tarjeta, Long> {
}