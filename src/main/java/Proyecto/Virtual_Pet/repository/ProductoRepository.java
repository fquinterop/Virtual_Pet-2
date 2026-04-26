package Proyecto.Virtual_Pet.repository;

import Proyecto.Virtual_Pet.model.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByUbicacionCiudad(String ciudad);

}