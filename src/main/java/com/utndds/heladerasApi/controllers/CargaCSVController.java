package com.utndds.heladerasApi.controllers;

import com.utndds.heladerasApi.services.CargaCSV.CargaCSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CargaCSVController {
    @Autowired
    private CargaCSV cargaCSV;

    @RequestMapping(value = "/cargarCSV", method = RequestMethod.POST)
    public void cargarArchivoCSV(String filePath) {
        cargaCSV.cargarCSV(filePath);
    }
}