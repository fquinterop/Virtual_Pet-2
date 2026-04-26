package Proyecto.Virtual_Pet.repository;

import Proyecto.Virtual_Pet.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByNombreContainingIgnoreCase(String nombre);
}
