package com.utndds.heladerasApi.controllers;

import com.utndds.heladerasApi.models.Colaboraciones.Colaboracion;
import com.utndds.heladerasApi.services.CargaCSV.CargaCSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CargaCSVController {
    @Autowired
    private CargaCSV cargaCSV = new CargaCSV();

    @RequestMapping(value = "/cargarCSV", method = RequestMethod.POST)
    public void cargarArchivoCSV(String filePath) {
        List<Colaboracion> colaboraciones = cargaCSV.cargarCSV(filePath);
        for (Colaboracion colaboracion : colaboraciones) {
            System.out.println("colaboracion cargada de: " + colaboracion.getColaborador().getPersona().getNombre());
        }
    }

    // PARA PROBAR EL CONTROLADOR
    public static void main(String[] args) {
        CargaCSVController controller = new CargaCSVController();
        String rutaArchivoCSV = ".\\src\\main\\resources\\colaboraciones.csv";
        controller.cargarArchivoCSV(rutaArchivoCSV);
    }
}