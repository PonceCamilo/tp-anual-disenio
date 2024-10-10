package com.utndds.heladerasApi.models.Persona;

import com.utndds.heladerasApi.models.Persona.Contacto.Contacto;
import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "persona_humana")
public class PersonaHumana extends Persona {

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaDeNacimiento;

    @ManyToOne
    @JoinColumn(name = "documento")
    private Documento documento;

    // Constructor vacío para JPA
    public PersonaHumana() {
    }

    public PersonaHumana(String direccion, List<Contacto> mediosContacto) {
        super(direccion, mediosContacto);
    }

    public PersonaHumana(String direccion, List<Contacto> mediosContacto, String nombre, String apellido,
            LocalDate fechaDeNacimiento, Documento documento) {
        super(direccion, mediosContacto);
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.documento = documento;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public Documento getDocumento() {
        return documento;
    }

}
