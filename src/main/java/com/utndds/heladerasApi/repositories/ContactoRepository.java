package com.utndds.heladerasApi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.utndds.heladerasApi.models.Persona.Persona;
import com.utndds.heladerasApi.models.Persona.Contacto.Contacto;

@Repository
public interface ContactoRepository extends JpaRepository<Contacto, Long> {
    @Query("SELECT c FROM Contacto c WHERE c.persona = :persona AND c.class IN :tiposContactos")
    List<Contacto> findByPersonaAndTipoIn(@Param("persona") Persona persona,
            @Param("tiposContactos") List<Class<? extends Contacto>> tiposContactos);
}