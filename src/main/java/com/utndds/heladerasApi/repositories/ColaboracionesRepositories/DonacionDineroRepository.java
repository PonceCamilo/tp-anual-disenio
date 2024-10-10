package com.utndds.heladerasApi.repositories.ColaboracionesRepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utndds.heladerasApi.models.Colaboraciones.DonacionDinero;

public interface DonacionDineroRepository extends JpaRepository<DonacionDinero, Long> {
}
