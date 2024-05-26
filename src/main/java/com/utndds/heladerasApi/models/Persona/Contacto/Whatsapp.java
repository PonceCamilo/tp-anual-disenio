package com.utndds.heladerasApi.models.Persona.Contacto;

public class Whatsapp extends Contacto{
    String numero;

    public Whatsapp(String numero) {
        this.numero = numero;
    }

    public void notificar() {
        System.out.println("SE NOTIFICO CON EXITO AL WHATSAPP: " + this.numero);
    }
}
