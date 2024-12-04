package com.utndds.heladerasApi.repositories.ColaboracionesRepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utndds.heladerasApi.models.Colaboraciones.Ofertas.Oferta;

public interface OfertaRepository extends JpaRepository<Oferta, Long> {
}
