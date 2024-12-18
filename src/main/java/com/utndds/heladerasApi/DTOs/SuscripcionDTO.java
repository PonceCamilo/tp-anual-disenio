package com.utndds.heladerasApi.DTOs;

import java.util.List;

import com.utndds.heladerasApi.models.Enums.*;

public class SuscripcionDTO {
    private String colaboradorUUID;
    private Long heladeraId;
    private List<TipoEvento> tiposEventosSeleccionados;
    private List<TipoContacto> tiposContactosSeleccionados;
    private int cantidadViandasMax;
    private int cantidadViandasMin;

    // Getters y Setters

    public String getColaboradorUUID() {
        return colaboradorUUID;
    }

    public void setColaboradorUUID(String colaboradorUUID) {
        this.colaboradorUUID = colaboradorUUID;
    }

    public Long getHeladeraId() {
        return heladeraId;
    }

    public void setHeladeraId(Long heladeraId) {
        this.heladeraId = heladeraId;
    }

    public List<TipoEvento> getTiposEventosSeleccionados() {
        return tiposEventosSeleccionados;
    }

    public void setTiposEventosSeleccionados(List<TipoEvento> tiposEventosSeleccionados) {
        this.tiposEventosSeleccionados = tiposEventosSeleccionados;
    }

    public List<TipoContacto> getTiposContactosSeleccionados() {
        return tiposContactosSeleccionados;
    }

    public void setTiposContactosSeleccionados(List<TipoContacto> tiposContactosSeleccionados) {
        this.tiposContactosSeleccionados = tiposContactosSeleccionados;
    }

    public int getCantidadViandasMax() {
        return cantidadViandasMax;
    }
    public int getCantidadViandasMin() {
        return cantidadViandasMin;
    }

    public void setCantidadViandasMax(int cantidadViandasMax) {
        this.cantidadViandasMax = cantidadViandasMax;
    }
    public void setCantidadViandasMin(int cantidadViandasMin) {
        this.cantidadViandasMin = cantidadViandasMin;
    }
}