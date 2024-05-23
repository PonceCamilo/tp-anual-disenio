package com.utndds.heladerasApi.models.Persona.Contacto;

public class Email {
    String email;

    public Email(String email) {
        this.email = email;
    }

    public void notificar() {
        System.out.println("SE NOTIFICO CON EXITO AL EMAIL: " + this.email);
    }
}
