package com.utndds.heladerasApi.models.Persona.Contacto;

public class Telefono extends Contacto {
    String numero;

    public Telefono(String numero) {
        this.numero = numero;
    }

    @Override
    public void notificar(String mensaje) {
        System.out.println("SE NOTIFICO CON EXITO AL TELEFONO: " + this.numero);
    }
}
