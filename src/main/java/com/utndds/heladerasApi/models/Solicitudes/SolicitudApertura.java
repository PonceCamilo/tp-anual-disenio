package com.utndds.heladerasApi.models.Solicitudes;

import java.time.LocalDateTime;

import com.utndds.heladerasApi.models.Enums.MotivoApertura;
import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Rol.Colaborador;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "solicitud_apertura")
public class SolicitudApertura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "colaborador")
    private Colaborador colaborador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "heladera")
    private Heladera heladera;

    @Column(name = "fecha_hora")
    private LocalDateTime fechaHora;

    @Column(name = "motivo")
    private MotivoApertura motivo;

    // Constructor vacío para JPA
    public SolicitudApertura() {
    }

    public SolicitudApertura(Colaborador colaborador, Heladera heladera, MotivoApertura motivo) {
        this.colaborador = colaborador;
        this.heladera = heladera;
        this.motivo = motivo;
        this.fechaHora = LocalDateTime.now();
    }

}
