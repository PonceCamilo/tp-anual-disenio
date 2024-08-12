package com.utndds.heladerasApi.models.Heladera.Incidentes;


import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Rol.Tecnico;

public class FallaTecnica extends Incidente {

    public FallaTecnica(Heladera heladera) {
        super(heladera);

        this.procesar();
        this.notificarTecnicoCercano();
    }

    @Override
    public void procesar() {

    };

    private void notificarTecnicoCercano() {
        Tecnico tecnico = new Tecnico(null, null, null, null);
        tecnico.notificar("Necesitamos tu servicio en la heladera " + this.heladera.getPunto().getNombre());

    }

}
