package com.utndds.heladerasApi.models.Persona.Contacto;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_contacto")
public abstract class Contacto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Métodos y atributos comunes si los hay
    public abstract void notificar(String mensaje);
}