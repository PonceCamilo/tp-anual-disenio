package com.utndds.heladerasApi.services.CargaCSV;

import java.util.ArrayList;
import java.util.List;

import com.utndds.heladerasApi.models.Persona.Persona;
import com.utndds.heladerasApi.models.Rol.Colaborador;
import com.utndds.heladerasApi.models.Colaboraciones.Colaboracion;

public class ColaboradorFactory {
    public Colaborador crearColaborador(String[] registro, Persona persona) {
        List<Colaboracion> colaboraciones = new ArrayList<>();
        Colaborador colaborador = new Colaborador(persona, colaboraciones);
        return colaborador;
    }

}