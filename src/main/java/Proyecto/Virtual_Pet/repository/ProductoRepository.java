package Proyecto.Virtual_Pet.repository;

import Proyecto.Virtual_Pet.model.entity.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByReferencia(String referencia);
    List<Producto> findByCategoria(String categoria);
    List<Producto> findByMarca(String marca);
    Page<Producto> findByActivoTrue(Pageable pageable);
}