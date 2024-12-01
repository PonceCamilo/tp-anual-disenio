package com.utndds.heladerasApi.services.ABM;
import com.utndds.heladerasApi.DTOs.PersonaHumanaDTO;
import com.utndds.heladerasApi.DTOs.PersonaJuridicaDTO;
import com.utndds.heladerasApi.models.Persona.Documento;
import com.utndds.heladerasApi.models.Persona.Persona;
import com.utndds.heladerasApi.models.Persona.PersonaHumana;
import com.utndds.heladerasApi.repositories.PersonaRepository;
import com.utndds.heladerasApi.services.RolService;
import com.utndds.heladerasApi.models.Persona.PersonaJuridica;
import com.utndds.heladerasApi.models.Persona.Contacto.Contacto;
import com.utndds.heladerasApi.models.Persona.Contacto.Email;
import com.utndds.heladerasApi.models.Persona.Contacto.Telegram;
import com.utndds.heladerasApi.models.Persona.Contacto.Whatsapp;
import com.utndds.heladerasApi.models.Rol.Colaborador;
import java.util.ArrayList;
import java.util.List;
import com.utndds.heladerasApi.repositories.ColaboradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    public Long crearPersonaHumana(PersonaHumanaDTO personaHumanaDTO) {
        // Crear los contactos a partir de los datos del DTO
        List<Contacto> mediosContacto = UserService.crearContactos(
            personaHumanaDTO.getMediosContacto(),
            personaHumanaDTO.getEmail(),
            personaHumanaDTO.getWhatsapp(),
            personaHumanaDTO.getTelegram()
        );
    
        // Crear el documento solo si ambos campos están presentes
        Documento documento = null; // Inicializar como null
        if (personaHumanaDTO.getTipo() != null && !personaHumanaDTO.getTipo().isEmpty() &&
            personaHumanaDTO.getDocumento() != null && !personaHumanaDTO.getDocumento().isEmpty()) {
            documento = UserService.crearDocumento(personaHumanaDTO.getTipo(), personaHumanaDTO.getDocumento());
        }
    
        // Crear la instancia de PersonaHumana con el documento (puede ser null)
        PersonaHumana personaHumana = new PersonaHumana(
            personaHumanaDTO.getDireccion(),
            mediosContacto,
            personaHumanaDTO.getNombre(),
            personaHumanaDTO.getApellido(),
            personaHumanaDTO.getFechaNacimiento(),
            documento // Se pasa el documento (puede ser null)
        );
        
        // Guardar en el repositorio
        personaRepository.save(personaHumana);
        Long id = personaHumana.getId();
        System.out.println("PersonaHumana guardada exitosamente");
        return id;
    }

    public Long crearPersonaJuridica(PersonaJuridicaDTO personaJuridicaDTO) {
        
        List <Contacto> mediosContacto = UserService.crearContactos(
            personaJuridicaDTO.getMediosContacto(),
            personaJuridicaDTO.getEmail(),
            personaJuridicaDTO.getWhatsapp(),
            personaJuridicaDTO.getTelegram()
        );
    
        PersonaJuridica personaJuridica = new PersonaJuridica(
            personaJuridicaDTO.getDireccion(),
            mediosContacto,
            personaJuridicaDTO.getRazonSocial(),
            personaJuridicaDTO.getTipo(),
            personaJuridicaDTO.getRubro()
        );

        personaRepository.save(personaJuridica);
        Long id = personaJuridica.getId();
        System.out.println("PersonaJuridica guardada exitosamente");
        return id;
    }
    
    public void crearColaborador(String uuid, String id) {
        // Buscar persona por ID
        Persona persona = personaRepository.findById(Long.parseLong(id))
                .orElseThrow(() -> new IllegalArgumentException("La persona no existe."));
        System.out.println("---------------la persona encontrada tiene el id--------------- " + persona.getId());
        
        // Crear nuevo colaborador
        Colaborador colaborador = new Colaborador(persona);
        colaborador.setUUID(uuid);
        // Guardar el colaborador en la base de datos (asumiendo que tienes un repositorio para eso)
        colaboradorRepository.save(colaborador);
    }
    
    
    public static List<Contacto> crearContactos(List<String> mediosContacto, String valorEmail , String valorWhatsapp, String valorTelegram) {
        List<Contacto> contactos = new ArrayList<>();

        for (String medio : mediosContacto) {
            switch (medio.toUpperCase()) {
                case "EMAIL":
                    contactos.add(new Email(valorEmail));
                    break;
                case "WHATSAPP":
                    contactos.add(new Whatsapp(valorWhatsapp));
                    break;
                case "TELEGRAM":
                    contactos.add(new Telegram(valorTelegram));
                    break;
                default:
                    throw new IllegalArgumentException("Medio de contacto no soportado: " + medio);
            }
        }

        return contactos;
    }

     public static Documento crearDocumento(String tipo, String numero) {
        // Validar tipo
        if (tipo == null || tipo.isEmpty()) {
            throw new IllegalArgumentException("El tipo de documento no puede estar vacío.");
        }

        // Validar número
        if (numero == null || numero.isEmpty()) {
            throw new IllegalArgumentException("El número de documento no puede estar vacío.");
        }

        if (!numero.matches("\\d{1,10}")) {
            throw new IllegalArgumentException("El número de documento debe contener entre 1 y 10 dígitos.");
        }

        // Crear y devolver el documento
        return new Documento(tipo.toUpperCase(), numero);
    }
}
