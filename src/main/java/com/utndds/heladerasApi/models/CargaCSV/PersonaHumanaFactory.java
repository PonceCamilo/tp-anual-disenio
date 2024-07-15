package com.utndds.heladerasApi.models.CargaCSV;

import com.utndds.heladerasApi.models.Persona.PersonaHumana;

public class PersonaHumanaFactory {
    public PersonaHumana crearPersonaHumana(String[] registro) {
        PersonaHumana persona = new PersonaHumana();
        persona.setNombre(registro[2]);
        persona.setApellido(registro[3]);
        return persona;
    }
}
