package Proyecto.Virtual_Pet.service;

import Proyecto.Virtual_Pet.model.entity.Pedido;
import Proyecto.Virtual_Pet.model.enums.EstadoPedido;
import Proyecto.Virtual_Pet.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido guardar(Pedido pedido) {
        if (pedido.getEstado() == null) {
            pedido.setEstado(EstadoPedido.PENDIENTE);
        }
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    public List<Pedido> buscarPorCliente(Long clienteId) {
        return pedidoRepository.findByClienteId(clienteId);
    }

    public List<Pedido> buscarPorEstado(EstadoPedido estado) {
        return pedidoRepository.findByEstado(estado);
    }

    public void eliminar(Long id) {
        pedidoRepository.deleteById(id);
    }

    public Pedido actualizar(Long id, Pedido pedido) {
        pedido.setId(id);
        return pedidoRepository.save(pedido);
    }

    public Pedido cambiarEstado(Long id, EstadoPedido nuevoEstado) {
        Pedido pedido = pedidoRepository.findById(id).orElse(null);
        if (pedido != null) {
            pedido.setEstado(nuevoEstado);
            return pedidoRepository.save(pedido);
        }
        return null;
    }
}
