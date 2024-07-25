package com.utndds.heladerasApi.services.CargaCSV;

import java.util.ArrayList;
import java.util.List;

import com.utndds.heladerasApi.models.Persona.PersonaHumana;
import com.utndds.heladerasApi.models.Persona.Contacto.Contacto;
import com.utndds.heladerasApi.models.Persona.Contacto.Email;

public class PersonaHumanaFactory {
    public PersonaHumana crearPersonaHumana(String[] registro) {

        List<Contacto> contactos = new ArrayList<>();
        Email email = new Email(registro[4]);
        contactos.add(email);
        PersonaHumana persona = new PersonaHumana(null, contactos);
        persona.setNombre(registro[2]);
        persona.setApellido(registro[3]);
        return persona;
    }
}
