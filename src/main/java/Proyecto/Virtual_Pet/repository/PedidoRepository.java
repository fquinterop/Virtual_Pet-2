package Proyecto.Virtual_Pet.repository;

import Proyecto.Virtual_Pet.model.entity.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByEstado(String estado);
    List<Pedido> findByClienteNombreContainingIgnoreCase(String nombre);
    Page<Pedido> findByEstado(String estado, Pageable pageable);
}