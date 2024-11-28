package com.utndds.heladerasApi.services.ABM;
import com.utndds.heladerasApi.DTOs.PersonaHumanaDTO;
import com.utndds.heladerasApi.DTOs.PersonaJuridicaDTO;
import com.utndds.heladerasApi.models.Persona.PersonaHumana;
import com.utndds.heladerasApi.repositories.PersonaRepository;
import com.utndds.heladerasApi.models.Persona.PersonaJuridica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private PersonaRepository personaRepository;

    public void crearPersonaHumana(PersonaHumanaDTO personaHumanaDTO) {
        PersonaHumana personaHumana = new PersonaHumana(personaHumanaDTO.getDireccion(),
        personaHumanaDTO.getMediosContacto(),
        personaHumanaDTO.getNombre(),
        personaHumanaDTO.getApellido(),
        personaHumanaDTO.getFechaNacimiento(),
        personaHumanaDTO.getDocumento()
        );
        personaRepository.save(personaHumana);

    }

    public void crearPersonaJuridica(PersonaJuridicaDTO personaJuridicaDTO) {
        PersonaJuridica personaJuridica = new PersonaJuridica(personaJuridicaDTO.getDireccion(),
        personaJuridicaDTO.getMediosContacto(),
        personaJuridicaDTO.getRazonSocial(),
        personaJuridicaDTO.getTipo(),
        personaJuridicaDTO.getRubro()
        
        );
        personaRepository.save(personaJuridica);
    }
}
