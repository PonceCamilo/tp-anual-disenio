package com.utndds.heladerasApi.controllers.DTOs;

public class DistribucionViandasDTO {
    private Long heladeraOrigenId;
    private Long heladeraDestinoId;
    private int cantidadViandasAMover;
    private String motivo;

    // Getters y Setters
    public Long getHeladeraOrigenId() {
        return heladeraOrigenId;
    }

    public void setHeladeraOrigenId(Long heladeraOrigenId) {
        this.heladeraOrigenId = heladeraOrigenId;
    }

    public Long getHeladeraDestinoId() {
        return heladeraDestinoId;
    }

    public void setHeladeraDestinoId(Long heladeraDestinoId) {
        this.heladeraDestinoId = heladeraDestinoId;
    }

    public int getCantidadViandasAMover() {
        return cantidadViandasAMover;
    }

    public void setCantidadViandasAMover(int cantidadViandasAMover) {
        this.cantidadViandasAMover = cantidadViandasAMover;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}