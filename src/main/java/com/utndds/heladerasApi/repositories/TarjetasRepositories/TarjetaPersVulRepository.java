package com.utndds.heladerasApi.repositories.TarjetasRepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utndds.heladerasApi.models.Tarjetas.TarjetaPersVuln;

public interface TarjetaPersVulRepository extends JpaRepository<TarjetaPersVuln, Long> {
}