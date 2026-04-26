package Proyecto.Virtual_Pet.repository;

import Proyecto.Virtual_Pet.model.entity.Pedido;
import Proyecto.Virtual_Pet.model.enums.EstadoPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByClienteId(Long clienteId);
    List<Pedido> findByEstado(EstadoPedido estado);
}
