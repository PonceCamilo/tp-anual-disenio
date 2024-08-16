package com.utndds.heladerasApi.models.Persona;

import java.util.List;

import com.utndds.heladerasApi.models.Persona.Contacto.Contacto;
import jakarta.persistence.*;

@Entity
@Table(name = "persona_juridica")
public class PersonaJuridica extends Persona {

    @Column(name = "razon_social")
    private String razonSocial;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "rubro")
    private String rubro;

    // Constructor vacío para JPA
    public PersonaJuridica() {
    }

    public PersonaJuridica(String direccion, List<Contacto> mediosContacto, String razonSocial, String tipo,
            String rubro) {
        super(direccion, mediosContacto);
        this.razonSocial = razonSocial;
        this.tipo = tipo;
        this.rubro = rubro;
    }

    @Override
    public String getNombre() {
        return this.razonSocial;
    };

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRubro() {
        return rubro;
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
