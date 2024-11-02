package com.utndds.heladerasApi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.utndds.heladerasApi.repositories.ColaboradorRepository;
import com.utndds.heladerasApi.repositories.TecnicoRepository;
import com.utndds.heladerasApi.DTOs.ColaboradorDTO;
import com.utndds.heladerasApi.DTOs.TecnicoDTO;
import com.utndds.heladerasApi.models.Rol.Colaborador;
import com.utndds.heladerasApi.models.Rol.Tecnico.Tecnico;

import java.util.Optional;

@Service
public class RolService {
    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    public ResponseEntity<?> obtenerRolPorUUID(String uuid) {
        Optional<Tecnico> tecnico = tecnicoRepository.findByUUID(uuid);
        if (tecnico.isPresent()) {
            TecnicoDTO tecnicoDTO = convertirATecnicoDTO(tecnico.get());
            return ResponseEntity.ok(tecnicoDTO);
        }

        Optional<Colaborador> colaborador = colaboradorRepository.findByUUID(uuid);
        if (colaborador.isPresent()) {
            ColaboradorDTO colaboradorDTO = convertirAColaboradorDTO(colaborador.get());
            return ResponseEntity.ok(colaboradorDTO);
        }

        return ResponseEntity.notFound().build(); 
    }

    private TecnicoDTO convertirATecnicoDTO(Tecnico tecnico) {
        TecnicoDTO dto = new TecnicoDTO();
        
        dto.setCuil(tecnico.getCuil());
        dto.setUuid(tecnico.getUUID());
        return dto;
    }

    private ColaboradorDTO convertirAColaboradorDTO(Colaborador colaborador) {
        ColaboradorDTO dto = new ColaboradorDTO();
        dto.setPuntosGastados(colaborador.getPuntosGastados());
        dto.setUuid(colaborador.getUUID()); // Asegúrate de tener el setter en el DTO
        return dto;
    }
}
