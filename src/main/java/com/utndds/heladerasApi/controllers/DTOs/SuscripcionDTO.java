package com.utndds.heladerasApi.controllers.DTOs;

import java.util.List;

import com.utndds.heladerasApi.models.Enums.*;;;

public class SuscripcionDTO {
    private Long colaboradorId;
    private Long heladeraId;
    private List<TipoEvento> tiposEventosSeleccionados;
    private List<TipoContacto> tiposContactosSeleccionados;
    private int cantidadViandas;

    // Getters y Setters

    public Long getColaboradorId() {
        return colaboradorId;
    }

    public void setColaboradorId(Long colaboradorId) {
        this.colaboradorId = colaboradorId;
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

    public int getCantidadViandas() {
        return cantidadViandas;
    }

    public void setCantidadViandas(int cantidadViandas) {
        this.cantidadViandas = cantidadViandas;
    }
}