package com.utndds.heladerasApi.models.Rol;

import java.util.List;

import com.utndds.heladerasApi.models.Heladera.VisitaTecnico;
import com.utndds.heladerasApi.models.Persona.Persona;

public class Tecnico extends Rol {
    String cuil;
    String areaCobertura;
    List<VisitaTecnico> visitas;

    public Tecnico(Persona persona, String cuil, String areaCobertura,
            List<VisitaTecnico> visitas) {
        super(persona);
        this.cuil = cuil;
        this.areaCobertura = areaCobertura;
        this.visitas = visitas;
    }

    public void setAreaCobertura(String areaCobertura) {
        this.areaCobertura = areaCobertura;
    }

    public String getAreaCobertura() {
        return areaCobertura;
    }

    public void setVisitas(List<VisitaTecnico> visitas) {
        this.visitas = visitas;
    }

    public List<VisitaTecnico> getVisitas() {
        return visitas;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public String getCuil() {
        return cuil;
    }

}
