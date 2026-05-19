package Proyecto.Virtual_Pet.repository;

import Proyecto.Virtual_Pet.model.entity.AdminUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AdminUsuarioRepository extends JpaRepository<AdminUsuario, Long> {
    Optional<AdminUsuario> findByUsername(String username);
    boolean existsByUsername(String username);
}