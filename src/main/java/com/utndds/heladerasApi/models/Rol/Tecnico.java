package com.utndds.heladerasApi.models.Rol;

import java.util.List;

import com.utndds.heladerasApi.models.Heladera.Incidentes.VisitaTecnico;
import com.utndds.heladerasApi.models.Persona.Persona;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tecnico")
public class Tecnico extends Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private Long id;
    @Column(name = "cuil")
    private String cuil;

    @Column(name = "area_cobertura")
    private String areaCobertura;

    @OneToMany(mappedBy = "tecnico", fetch = FetchType.LAZY)
    private List<VisitaTecnico> visitas;

    // Constructor vacío para JPA
    public Tecnico() {
    }

    public Tecnico(Persona persona, String cuil, String areaCobertura,
            List<VisitaTecnico> visitas) {
        super(persona);
        this.cuil = cuil;
        this.areaCobertura = areaCobertura;
        this.visitas = visitas;
    }

    public void setAreaCobertura(String areaCobertura) {
        this.areaCobertura = areaCobertura;
    }

    public String getAreaCobertura() {
        return areaCobertura;
    }

    public void setVisitas(List<VisitaTecnico> visitas) {
        this.visitas = visitas;
    }

    public List<VisitaTecnico> getVisitas() {
        return visitas;
    }

    public void agregarVisita(VisitaTecnico visita) {
        visitas.add(visita);
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public String getCuil() {
        return cuil;
    }

}
