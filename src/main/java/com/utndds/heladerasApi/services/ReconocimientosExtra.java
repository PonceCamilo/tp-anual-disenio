package com.utndds.heladerasApi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utndds.heladerasApi.models.Colaboraciones.Colaboracion;
import com.utndds.heladerasApi.models.Colaboraciones.DonacionViandas.DonacionVianda;
import com.utndds.heladerasApi.models.Rol.Colaborador;
import com.utndds.heladerasApi.repositories.ColaboradorRepository;
import com.utndds.heladerasApi.repositories.ColaboracionesRepositories.ColaboracionRepository;
import com.utndds.heladerasApi.services.Canjes.CalculadoraPuntosService;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

@Service
public class ReconocimientosExtra {
    @Autowired
    private CalculadoraPuntosService calculadoraPuntosService;

    @Autowired
    private ColaboradorRepository colaboradorRepository; // Inyección del repositorio

    @Autowired
    private ColaboracionRepository colaboracionRepository;

    public List<Colaborador> recomendarColaboradores(double puntosReq, double viandasDonadasReq,
            int cantMaxColabs) {
        List<Colaborador> colaboradoresRecomendados = new ArrayList<>();

        // Obtener la lista de todos los colaboradores desde la base de datos
        List<Colaborador> colaboradores = colaboradorRepository.findAll();

        // Filtrar los colaboradores que cumplen con los requisitos
        for (Colaborador colaborador : colaboradores) {
            double puntosColaborador = calculadoraPuntosService.calcularPuntos(colaborador.getId());

            int viandasDonadasUltimoMes = getViandasDonadas(colaborador, 30);

            if (puntosColaborador >= puntosReq && viandasDonadasUltimoMes >= viandasDonadasReq) {
                colaboradoresRecomendados.add(colaborador);
            }
            if (colaboradoresRecomendados.size() >= cantMaxColabs) {
                break;
            }
        }

        return colaboradoresRecomendados;
    }

    public int getViandasDonadas(Colaborador colaborador, int dias) {
        int viandasDonadas = 0;
        LocalDate fechaInicio = LocalDate.now().minusDays(dias); // Define el inicio del rango de tiempo

        List<Colaboracion> colaboraciones = colaboracionRepository.findByColaborador(colaborador);
        for (Colaboracion colaboracion : colaboraciones) {
            if (colaboracion instanceof DonacionVianda) {
                DonacionVianda donacion = (DonacionVianda) colaboracion;
                if (donacion.getFecha().isAfter(fechaInicio)) {
                    viandasDonadas++;
                }
            }
        }
        return viandasDonadas;
    }

}