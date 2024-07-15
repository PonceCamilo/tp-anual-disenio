package com.utndds.heladerasApi.models.Heladera;

import com.utndds.heladerasApi.models.Rol.Colaborador;

public class SolicitudApertura {
    Colaborador colaborador;
    Heladera heladera;

    public SolicitudApertura(Colaborador colaborador, Heladera heladera) {
        this.colaborador = colaborador;
        this.heladera = heladera;

        this.heladera.agregarSolicitud(this);
        this.iniciarTemporizador();
    }

    public Colaborador getColaborador() {
        return this.colaborador;
    }

    public void iniciarTemporizador() {
        System.out.println("temporizador iniciado para abrir: " + heladera.getNombre() + "por parte de "
                + colaborador.getPersona().getNombre());
    }

    public int leerTiempoDesdeJson(String filePath) {
        return 3;
    }

    public static void main(String[] args) {
    }

}
