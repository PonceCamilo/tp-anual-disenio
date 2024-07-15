package com.utndds.heladerasApi.models.Colaboraciones;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utndds.heladerasApi.models.Rol.Colaborador;
import com.utndds.heladerasApi.models.Rol.PersonaVulnerable;

public class RegistroPersonaVulnerable extends Colaboracion {
    PersonaVulnerable personaVuln;

    public RegistroPersonaVulnerable(LocalDate fecha, Colaborador colaborador, PersonaVulnerable personaVuln) {
        super(fecha, colaborador);
        this.personaVuln = personaVuln;
    }

    @Override
    public void procesar() {
        System.out.println("SE REALIZO EL REGISTRO DE PERSONA VULNERABLE");
    }

    @Override
    public double puntosGanados() {
        System.out.println("CORREGIR PUNTOS GANADOS");
        return this.obtenerCoeficiente();
    }

    @Override
    protected double obtenerCoeficiente() {
        try {
            String projectDir = Paths.get("").toAbsolutePath().toString();
            String jsonFilePath = projectDir + "/src/main/resources/coeficientes.json";
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(new File(jsonFilePath));
            JsonNode coeficientesNode = rootNode.path("coeficientes");
            return coeficientesNode.path("TARJETAS_REPARTIDAS").asDouble();
        } catch (IOException e) {
            e.printStackTrace();
            // Maneja la excepción, aquí puedes lanzar una excepción personalizada si es
            // necesario
            return 0;
        }
    }

}
