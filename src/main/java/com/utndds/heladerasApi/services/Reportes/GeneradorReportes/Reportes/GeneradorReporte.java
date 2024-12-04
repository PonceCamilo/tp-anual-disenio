package com.utndds.heladerasApi.services.Reportes.GeneradorReportes.Reportes;

import java.time.LocalDate;

public interface GeneradorReporte {
    void generar(LocalDate fechaInicial, LocalDate fechaFinal);
}