package com.utndds.heladerasApi.models.Persona.Contacto;
import javax.persistence.*;

@Entity
@DiscriminatorValue("TELEFONO")
public class Telefono extends Contacto {
    @Column(name = "numero")
    private String numero;

    public Telefono() {
    }

    public Telefono(String numero) {
        this.numero = numero;
    }

    @Override
    public void notificar(String mensaje) {
        System.out.println("SE NOTIFICO CON EXITO AL TELEFONO: " + this.numero);
    }
}
