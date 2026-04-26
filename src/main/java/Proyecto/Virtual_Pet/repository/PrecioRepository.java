package Proyecto.Virtual_Pet.repository;

import Proyecto.Virtual_Pet.model.entity.Precio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrecioRepository extends JpaRepository<Precio, Long> {

}