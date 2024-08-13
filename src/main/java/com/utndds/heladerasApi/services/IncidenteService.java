package com.utndds.heladerasApi.services;

import java.util.List;

import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Heladera.Incidentes.Incidente;
import com.utndds.heladerasApi.models.Heladera.Incidentes.VisitaTecnico;
import com.utndds.heladerasApi.models.Rol.Tecnico;
import com.utndds.heladerasApi.models.Sistema.Sistema;
import org.springframework.stereotype.Service;

@Service
public class IncidenteService {

    public void reportarIncidente(Incidente incidente) {
        this.notificarTecnicoCercano(incidente.getHeladera());
        // LOGICA PARA REPORTARLO
    }

    public void registrarVisita(VisitaTecnico visita) {
        // LOGICA PARA REGISTRARLA
    }

    public void notificarTecnicoCercano(Heladera heladera) {
        Tecnico tecnico = this.obtenerTecnicoMasCercano(heladera);
        tecnico.getPersona()
                .notificar("NECESITAMOS TUS SERVICIOS EN LA DIRECCION: " + heladera.getPunto().getDireccion());
    }

    private Tecnico obtenerTecnicoMasCercano(Heladera heladera) {
        List<Tecnico> tecnicos = Sistema.getInstance().getTecnicos();
        String direccionHeladera = heladera.getPunto().getDireccion();

        Tecnico tecnicoMasCercano = null;
        double distanciaMinima = Double.MAX_VALUE;
        for (Tecnico tecnico : tecnicos) {
            String direccionTecnico = tecnico.getPersona().getDireccion();
            double distancia = calcularDistancia(direccionTecnico, direccionHeladera);
            if (distancia < distanciaMinima) {
                distanciaMinima = distancia;
                tecnicoMasCercano = tecnico;
            }
        }

        return tecnicoMasCercano;
    }

    private double calcularDistancia(String direccionTecnico, String direccionHeladera) {
        return 0; // FALTA IMPLEMENTAR
    }

}
