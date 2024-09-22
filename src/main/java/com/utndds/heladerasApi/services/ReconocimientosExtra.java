package com.utndds.heladerasApi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.utndds.heladerasApi.models.ONG.ONG;
import com.utndds.heladerasApi.models.Rol.Colaborador;

import java.util.List;
import java.util.ArrayList;

@Service
public class ReconocimientosExtra {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CalculadoraPuntosService calculadoraPuntosService; // Inyectar el servicio para calcular los puntos

    @Autowired
    private ConocerColaboracionesService conocerColaboracionesService; // Inyectar el servicio para calcular los puntos

    public List<Colaborador> recomendarColaboradores(double puntosReq, double viandasDonadasReq,
            int cantMaxColabs) {
        List<Colaborador> colaboradoresRecomendados = new ArrayList<>();

        // Obtener la instancia de la ONG
        ONG ong = ONG.getInstance();

        // Obtener la lista de todos los colaboradores
        List<Colaborador> colaboradores = ong.getColaboradores();

        // Filtrar los colaboradores que cumplen con los requisitos
        for (Colaborador colaborador : colaboradores) {

            double puntosColaborador = calculadoraPuntosService.calcularPuntos(colaborador);
            int viandasDonadasUltimoMes = conocerColaboracionesService.getViandasDonadas(colaborador, 30);

            if (puntosColaborador >= puntosReq && viandasDonadasUltimoMes >= viandasDonadasReq) {
                colaboradoresRecomendados.add(colaborador);
            }
            if (colaboradoresRecomendados.size() >= cantMaxColabs) {
                break;
            }
        }

        return colaboradoresRecomendados;
    }
}