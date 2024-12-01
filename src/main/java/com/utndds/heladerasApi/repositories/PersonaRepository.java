package com.utndds.heladerasApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.utndds.heladerasApi.models.Persona.*;
public interface PersonaRepository extends JpaRepository<Persona, Long> {
    PersonaHumana findByDocumento_TipoAndDocumento_Numero(String tipoDocumento, String numeroDocumento);
    
}