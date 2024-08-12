package com.utndds.heladerasApi.services.CargaCSV;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.utndds.heladerasApi.models.Persona.PersonaHumana;
import com.utndds.heladerasApi.models.Rol.Colaborador;
import com.utndds.heladerasApi.models.Sistema.Sistema;

import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Service
public class CargaCSV {
    private PersonaHumanaFactory phFactory = new PersonaHumanaFactory();
    private ColaboradorFactory cFactory = new ColaboradorFactory();
    private ColaboracionFactory colaboFactory = new ColaboracionFactory();

    public void cargarCSV(String filePath) {
        Sistema sistema = Sistema.getInstance();

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            List<String[]> registros = reader.readAll();
            for (String[] registro : registros) {
                PersonaHumana persona = phFactory.crearPersonaHumana(registro);

                Colaborador colaborador = sistema.buscarColaborador(registro[0], registro[1]);
                if (colaborador == null) {
                    colaborador = cFactory.crearColaborador(registro, persona);
                    sistema.agregarColaborador(colaborador);
                }

                colaboFactory.crearColaboracion(registro, colaborador);
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }
}