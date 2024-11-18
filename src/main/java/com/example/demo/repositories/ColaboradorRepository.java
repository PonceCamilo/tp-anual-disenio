package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.example.demo.models.Persona.PersonaHumana;
import com.example.demo.models.Rol.Colaborador;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {
    Colaborador findByPersona(PersonaHumana persona);

    @SuppressWarnings("null")
    Optional<Colaborador> findById(Long Id);

    Optional<Colaborador> findByUUID(String UUID);
}