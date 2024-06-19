package com.utndds.heladerasApi.models.CargaCSV;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.utndds.heladerasApi.models.Colaboraciones.Colaboracion;
import com.utndds.heladerasApi.models.Persona.Colaboradores.PersonaHumana;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class CargaCSV {
    private PersonaHumanaFactory phfactory = new PersonaHumanaFactory();
    private ColaboracionFactory cfactory = new ColaboracionFactory();

    public List<PersonaHumana> cargarCSV(String filePath) {
        List<PersonaHumana> personas = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            List<String[]> registros = reader.readAll();
            for (String[] registro : registros) {
                PersonaHumana persona = phfactory.crearPersonaHumana(registro);
                List<Colaboracion> colaboraciones = cfactory.crearColaboracion(registro, persona);
                persona.setColaboraciones(colaboraciones);
                personas.add(persona);
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return personas;
    }

    public static void main(String[] args) {

    }
}