package com.utndds.heladerasApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utndds.heladerasApi.models.Heladera.Heladera;
import java.util.Optional;

public interface HeladeraRepository extends JpaRepository<Heladera, Long> {
    Optional<Heladera> findById(Long id);

}
