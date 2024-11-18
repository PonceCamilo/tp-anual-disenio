package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Colaboraciones.Colaboracion;
import com.example.demo.models.Rol.Colaborador;

@Repository
public interface ColaboracionRepository extends JpaRepository<Colaboracion, Long> {

    List<Colaboracion> findByColaborador(Colaborador colaborador);
    // Aquí puedes agregar métodos personalizados si lo deseas
}