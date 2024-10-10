package com.utndds.heladerasApi.services.CargaCSV;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.utndds.heladerasApi.models.Colaboraciones.Colaboracion;
import com.utndds.heladerasApi.models.Persona.PersonaHumana;
import com.utndds.heladerasApi.models.Rol.Colaborador;
import com.utndds.heladerasApi.repositories.ColaboradorRepository;
import com.utndds.heladerasApi.repositories.PersonaRepository;
import com.utndds.heladerasApi.repositories.ColaboracionesRepositories.ColaboracionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Service
public class CargaCSV {

    private final PersonaHumanaFactory phFactory = new PersonaHumanaFactory();
    private final ColaboradorFactory cFactory = new ColaboradorFactory();
    private final ColaboracionFactory colaboFactory = new ColaboracionFactory();

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private ColaboracionRepository colaboracionRepository;

    public void cargarCSV(InputStream fileInputStream) {
        try (CSVReader reader = new CSVReader(new InputStreamReader(fileInputStream))) {
            List<String[]> registros = reader.readAll();
            for (String[] registro : registros) {
                if (registro.length < 5) {
                    throw new IllegalArgumentException(
                            "Registro debe tener al menos 5 campos. El actual tiene: " + registro.length);
                }

                // Proceed with creating the PersonaHumana
                PersonaHumana persona = phFactory.crearPersonaHumana(registro);

                // Buscar la persona en la base de datos por su documento
                PersonaHumana personaExistente = personaRepository.findByDocumento_TipoAndDocumento_Numero(
                        registro[0], registro[1]);

                if (personaExistente == null) {
                    personaRepository.save(persona); // Guardar nueva persona si no existe
                } else {
                    persona = personaExistente; // Usar la persona existente
                }

                // Buscar el colaborador en la base de datos por la persona asociada
                Colaborador colaborador = colaboradorRepository.findByPersona(persona);

                if (colaborador == null) {
                    colaborador = cFactory.crearColaborador(registro, persona);
                    colaboradorRepository.save(colaborador); // Guardar nuevo colaborador si no existe
                }

                // Crear colaboraciones y guardarlas en la base de datos
                List<Colaboracion> colaboraciones = colaboFactory.crearColaboracion(registro, colaborador);
                colaboracionRepository.saveAll(colaboraciones); // Guardar todas las colaboraciones
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }
}