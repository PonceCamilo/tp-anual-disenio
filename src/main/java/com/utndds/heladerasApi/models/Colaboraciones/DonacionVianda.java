package com.utndds.heladerasApi.models.Colaboraciones;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Heladera.Vianda;
import com.utndds.heladerasApi.models.Rol.Colaborador;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DonacionVianda extends Colaboracion {
    List<Vianda> viandasDonadas = new ArrayList<>();
    Heladera heladera;
    boolean estado;

    public DonacionVianda(
            LocalDate fecha, Colaborador colaborador,
            List<Vianda> viandasDonadas, Heladera heladera,
            boolean estado) {
        super(fecha, colaborador);
        this.viandasDonadas = viandasDonadas;
        this.heladera = heladera;
        this.estado = estado;
    }

    @Override
    public void procesar() {
        System.out.println("SE REALIZO LA DONACION DE VIANDA");
    }

    @Override
    public double puntosGanados() {
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
            return coeficientesNode.path("VIANDAS_DONADAS").asDouble();
        } catch (IOException e) {
            e.printStackTrace();
            // Maneja la excepción, aquí puedes lanzar una excepción personalizada si es
            // necesario
            return 0;
        }
    }

}
