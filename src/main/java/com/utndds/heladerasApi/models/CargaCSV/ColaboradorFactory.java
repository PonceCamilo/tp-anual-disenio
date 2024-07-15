package com.utndds.heladerasApi.models.CargaCSV;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.utndds.heladerasApi.models.Persona.Persona;
import com.utndds.heladerasApi.models.Rol.Colaborador;
import com.utndds.heladerasApi.models.Colaboraciones.Colaboracion;
import com.utndds.heladerasApi.models.Rol.Contacto.Email;

public class ColaboradorFactory {
    public Colaborador crearColaborador(String[] registro, Persona persona) {
        List<Colaboracion> colaboraciones = new ArrayList<>();
        Email email = new Email(registro[4]);
        Colaborador colaborador = new Colaborador(persona, (Arrays.asList(email)), null, colaboraciones);
        return colaborador;
    }

}