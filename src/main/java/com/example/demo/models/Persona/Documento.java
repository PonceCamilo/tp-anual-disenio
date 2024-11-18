package com.example.demo.models.Persona;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "documento")
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "numero", nullable = false, unique = true)
    private String numero;

    // Constructor vacío para JPA
    public Documento() {
    }

    public Documento(String tipo, String numero) {
        this.tipo = tipo;
        this.numero = numero;
    }

}
