package com.utndds.heladerasApi.services.Colaboraciones;

import org.springframework.beans.factory.annotation.Autowired;

import com.utndds.heladerasApi.models.Colaboraciones.DonacionDinero;
import com.utndds.heladerasApi.models.Rol.Colaborador;
import com.utndds.heladerasApi.repositories.ColaboradorRepository;
import com.utndds.heladerasApi.repositories.ColaboracionesRepositories.DonacionDineroRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import jakarta.persistence.EntityNotFoundException;

@Service
public class DonacionDineroService {

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @Autowired
    private DonacionDineroRepository donacionDineroRepository;

    public void guardarDonacionDinero(double monto, String colaboradorUUID) {

        Colaborador colaborador = colaboradorRepository.findByUUID(colaboradorUUID)
                .orElseThrow(
                        () -> new EntityNotFoundException("Colaborador no encontrado con uuid " + colaboradorUUID));

        // Crear la persona vulnerable con los datos del formulario
        DonacionDinero donacionDinero = new DonacionDinero();
        donacionDinero.setColaborador(colaborador);
        donacionDinero.setFecha(LocalDate.now()); // Fecha actual
        donacionDinero.setMonto(monto);

        donacionDineroRepository.save(donacionDinero);
    }
}