package com.utndds.heladerasApi.services.CargaCSV;

import com.utndds.heladerasApi.models.Persona.Persona;
import com.utndds.heladerasApi.models.Rol.Colaborador;

public class ColaboradorFactory {
    public Colaborador crearColaborador(String[] registro, Persona persona) {
        Colaborador colaborador = new Colaborador(persona);
        return colaborador;
    }

}