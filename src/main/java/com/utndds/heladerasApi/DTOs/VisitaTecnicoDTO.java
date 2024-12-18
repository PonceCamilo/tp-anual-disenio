package com.utndds.heladerasApi.DTOs;

public class VisitaTecnicoDTO {

    private Long incidenteId;
    private String comentario;
    private String foto; // Puede ser una URL o base64 para la imagen
    private boolean solucionado;

    // Getters y Setters

    public Long getIncidenteId() {
        return incidenteId;
    }

    public void setIncidenteId(Long incidenteId) {
        this.incidenteId = incidenteId;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public boolean isSolucionado() {
        return solucionado;
    }

    public void setSolucionado(boolean solucionado) {
        this.solucionado = solucionado;
    }
}