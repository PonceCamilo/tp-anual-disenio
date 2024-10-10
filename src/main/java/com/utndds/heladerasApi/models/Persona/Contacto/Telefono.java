package com.utndds.heladerasApi.models.Persona.Contacto;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("TELEFONO")
public class Telefono extends Contacto {

    public Telefono() {
    }

    public Telefono(String numero) {
        this.valor = numero;
    }

    @Override
    public void notificar(String mensaje) {
        System.out.println("SE NOTIFICO CON EXITO AL TELEFONO: " + this.valor);
    }
}
