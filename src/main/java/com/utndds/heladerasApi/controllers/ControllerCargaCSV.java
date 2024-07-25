package com.utndds.heladerasApi.controllers;

import com.utndds.heladerasApi.models.Colaboraciones.Colaboracion;
import com.utndds.heladerasApi.services.CargaCSV.CargaCSV;

import java.util.List;

public class ControllerCargaCSV {

    public void cargarArchivoCSV(String filePath) {
        CargaCSV cargaCSV = new CargaCSV();
        List<Colaboracion> colaboraciones = cargaCSV.cargarCSV(filePath);
        for (Colaboracion colaboracion : colaboraciones) {
            System.out.println("colaboracion cargada de: " + colaboracion.getColaborador().getPersona().getNombre());
        }
    }

    // PARA PROBAR EL CONTROLADOR
    public static void main(String[] args) {
        ControllerCargaCSV controller = new ControllerCargaCSV();
        String rutaArchivoCSV = ".\\src\\main\\resources\\colaboraciones.csv";
        controller.cargarArchivoCSV(rutaArchivoCSV);
    }
}
