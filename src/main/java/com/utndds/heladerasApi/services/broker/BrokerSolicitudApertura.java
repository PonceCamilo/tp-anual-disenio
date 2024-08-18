package com.utndds.heladerasApi.services.broker;

import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Rol.Colaborador;

public class BrokerSolicitudApertura {

    public boolean solicitarApertura(Colaborador colaborador, Heladera heladera) {
        // Lógica de autorización (puedes implementar reglas de negocio aquí)
        boolean autorizado = verificarAutorizacion(colaborador, heladera);

        if (autorizado) {
            heladera.setAbierta(true);
            System.out.println(
                    "Heladera " + heladera.getId() + " ha sido abierta por " + colaborador.getPersona().getNombre());
        } else {
            System.out.println("Solicitud de apertura rechazada para " + colaborador.getPersona().getNombre());
        }

        return autorizado;
    }

    // Simulación de verificación de autorización (puede ser más compleja en un caso
    // real)
    private boolean verificarAutorizacion(Colaborador colaborador, Heladera heladera) {
        // Implementar la lógica para verificar si el colaborador tiene autorización
        // Por ejemplo, podrías verificar si el colaborador está registrado en la zona
        // de la heladera
        return colaborador.getTarjeta().puedeUsarse(heladera);
    }
}