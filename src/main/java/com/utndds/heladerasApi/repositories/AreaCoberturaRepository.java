package com.utndds.heladerasApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utndds.heladerasApi.models.Rol.Tecnico.AreaCobertura;

@Repository
public interface AreaCoberturaRepository extends JpaRepository<AreaCobertura, Long> {
}