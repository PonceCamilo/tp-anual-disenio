package com.utndds.heladerasApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.utndds.heladerasApi.models.Rol.PersonaVulnerable;

public interface PersonaVulnerableRepository extends JpaRepository<PersonaVulnerable, Long> {
}