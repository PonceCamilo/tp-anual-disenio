package com.utndds.heladerasApi.DTOs;
import java.time.LocalDate;
public class FallaTecnicaDTO {
    private Long heladeraId; // ID of the refrigerator
    private String descripcion; // Description of the incident
    private String foto; // Path or URL to the photo
    private LocalDate fecha; // Date of the incident

    // Default constructor
    public FallaTecnicaDTO() {
    }

    // Constructor with parameters
    public FallaTecnicaDTO(Long heladeraId, String descripcion, String foto, LocalDate fecha) {
        this.heladeraId = heladeraId;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.foto = foto;
    }

    // Getters and Setters
    public Long getHeladeraId() {
        return heladeraId;
    }

    public void setHeladeraId(Long heladeraId) {
        this.heladeraId = heladeraId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}