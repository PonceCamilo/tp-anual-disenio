package com.utndds.heladerasApi.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.utndds.heladerasApi.models.Rol.Rol;

public interface RolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByUUID(String UUID);
    
}
