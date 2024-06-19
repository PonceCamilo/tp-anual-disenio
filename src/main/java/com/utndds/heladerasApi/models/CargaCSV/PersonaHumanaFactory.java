package com.utndds.heladerasApi.models.CargaCSV;

import java.util.Arrays;
import com.utndds.heladerasApi.models.Persona.Colaboradores.PersonaHumana;
import com.utndds.heladerasApi.models.Persona.Contacto.Email;

public class PersonaHumanaFactory {
    public PersonaHumana crearPersonaHumana(String[] registro) {
        Email email = new Email(registro[4]);
        PersonaHumana persona = new PersonaHumana();
        persona.setNombre(registro[2]);
        persona.setApellido(registro[3]);
        persona.setMediosDeContacto(Arrays.asList(email));
        return persona;
    }
}
