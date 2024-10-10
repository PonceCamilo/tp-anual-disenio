package com.utndds.heladerasApi.repositories.ColaboracionesRepositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utndds.heladerasApi.models.Colaboraciones.Colaboracion;
import com.utndds.heladerasApi.models.Rol.Colaborador;

@Repository
public interface ColaboracionRepository extends JpaRepository<Colaboracion, Long> {

    List<Colaboracion> findByColaborador(Colaborador colaborador);
    // Aquí puedes agregar métodos personalizados si lo deseas
}