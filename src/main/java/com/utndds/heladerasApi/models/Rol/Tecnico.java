package com.utndds.heladerasApi.models.Rol;

public class Tecnico {
    String cuil;
    String areaCobertura;

    public Tecnico(String cuil, String areaCobertura) {
        this.cuil = cuil;
        this.areaCobertura = areaCobertura;
    }

    public void setAreaCobertura(String areaCobertura) {
        this.areaCobertura = areaCobertura;
    }

    public String getAreaCobertura() {
        return areaCobertura;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public String getCuil() {
        return cuil;
    }

}
