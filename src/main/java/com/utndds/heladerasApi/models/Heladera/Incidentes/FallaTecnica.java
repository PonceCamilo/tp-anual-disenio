package com.utndds.heladerasApi.models.Heladera.Incidentes;

import java.sql.Time;
import java.time.LocalDate;

import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Rol.Tecnico;

public class FallaTecnica extends Incidente {

    public FallaTecnica(LocalDate fecha, Time hora, Heladera heladera) {
        super(fecha, hora, heladera);

        this.procesar();
        this.notificarTecnicoCercano();
    }

    @Override
    public void procesar() {

    };

    private void notificarTecnicoCercano() {
        Tecnico tecnico = this.heladera.tecnicoMasCercano();
        tecnico.notificar("Necesitamos tu servicio en la heladera " + this.heladera.getNombre());

    }

}
