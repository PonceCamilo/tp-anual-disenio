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
import java.util.Arrays;

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
        System.out.println("ENTRAMO CSV...");
        try (CSVReader reader = new CSVReader(new InputStreamReader(fileInputStream))) {
            List<String[]> registros = reader.readAll();

            System.out.println("REGISTROS:");
            for (String[] registro : registros) {
                System.out.println(Arrays.toString(registro));
            }

            for (String[] registro : registros) {
                if (registro.length < 5) {
                    throw new IllegalArgumentException(
                            "Registro debe tener al menos 5 campos. El actual tiene: " + registro.length);
                }

                // Crear o buscar PersonaHumana
                PersonaHumana persona = getOrCreatePersonaHumana(registro);

                // Crear o buscar Colaborador
                Colaborador colaborador = getOrCreateColaborador(persona, registro);

                // Crear y guardar Colaboraciones
                List<Colaboracion> colaboraciones = colaboFactory.crearColaboracion(registro, colaborador);
                colaboracionRepository.saveAll(colaboraciones);
            }
        } catch (IOException | CsvException e) {
            System.out.println("Error al cargar el archivo CSV: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private PersonaHumana getOrCreatePersonaHumana(String[] registro) {
        PersonaHumana persona = personaRepository.findByDocumento_TipoAndDocumento_Numero(registro[0], registro[1]);

        if (persona == null) {
            persona = phFactory.crearPersonaHumana(registro);
            persona = personaRepository.save(persona); // Guardar nueva persona
        }
        return persona;
    }

    private Colaborador getOrCreateColaborador(PersonaHumana persona, String[] registro) {
        Colaborador colaborador = colaboradorRepository.findByPersona(persona);

        if (colaborador == null) {
            colaborador = cFactory.crearColaborador(registro, persona);
            colaborador = colaboradorRepository.save(colaborador); // Guardar nuevo colaborador
        }
        return colaborador;
    }
}