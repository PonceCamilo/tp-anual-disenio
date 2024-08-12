package com.utndds.heladerasApi.models.Colaboraciones;

import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Rol.Colaborador;

public class ObtencionHeladera extends Colaboracion {
    Heladera heladera;

    public ObtencionHeladera(Colaborador colaborador, Heladera heladera) {
        super(colaborador);
        this.heladera = heladera;
    }

    @Override
    protected void procesar() {
        super.procesar();
        System.out.println(
                "SE GUARDO LA OBTENCION DE HELADERA POR PARTE DE: " + this.colaborador.getPersona().getNombre());
    }

    public Heladera getHeladera() {
        return heladera;
    }

    public static void main(String[] args) {
        ObtencionHeladera colaboracion = new ObtencionHeladera(null, null);
        System.out.println(colaboracion.puntosGanados());

    }

}
