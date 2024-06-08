package com.utndds.heladerasApi.models.CargaCSV;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.utndds.heladerasApi.models.Persona.Colaboradores.PersonaHumana;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class CargaCSV {
    private PersonaHumanaFactory factory = new PersonaHumanaFactory();
    public List<PersonaHumana> cargarArchivoCSV(String filePath) {
        List<PersonaHumana> personas = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            List<String[]> registros = reader.readAll();
            for (String[] registro : registros) {
                PersonaHumana persona = factory.crearPersonaHumana(registro);
                personas.add(persona);
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return personas;
    }
}
