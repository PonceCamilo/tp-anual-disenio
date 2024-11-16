package com.utndds.heladerasApi.services.CargaCSV;

import java.util.ArrayList;
import java.util.List;

import com.utndds.heladerasApi.models.Persona.PersonaHumana;
import com.utndds.heladerasApi.models.Persona.Contacto.Contacto;
import com.utndds.heladerasApi.models.Persona.Contacto.Email;
import com.utndds.heladerasApi.models.Persona.Documento;

public class PersonaHumanaFactory {
    public PersonaHumana crearPersonaHumana(String[] registro) {

        List<Contacto> contactos = new ArrayList<>();
        Email email = new Email(registro[4]);
        Documento documento = new Documento(registro[0], registro[1]);
        contactos.add(email);
        PersonaHumana persona = new PersonaHumana(null, contactos);
        persona.setDocumento(documento);
        persona.setNombre(registro[2]);
        persona.setApellido(registro[3]);
        System.out.println(persona.getNombre());
        System.out.println(persona.getApellido());
        System.out.println(persona.getDocumento());
        System.out.println("PERSONA HUMANA CREADA");
        return persona;
    }
}
