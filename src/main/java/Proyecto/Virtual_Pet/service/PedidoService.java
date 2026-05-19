package Proyecto.Virtual_Pet.service;

import Proyecto.Virtual_Pet.model.entity.Pedido;
import Proyecto.Virtual_Pet.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido guardar(Pedido pedido) {
        pedido.setNumeroPedido(generarNumeroPedido());
        pedido.setEstado("PENDIENTE");
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado: " + id));
    }

    public List<Pedido> buscarPorCliente(Long clienteId) {
        return pedidoRepository.findAll().stream()
                .filter(p -> p.getId().equals(clienteId))
                .toList();
    }

    public List<Pedido> buscarPorEstado(String estado) {
        return pedidoRepository.findByEstado(estado);
    }

    public Pedido cambiarEstado(Long id, String estado) {
        Pedido pedido = buscarPorId(id);
        pedido.setEstado(estado);
        return pedidoRepository.save(pedido);
    }

    public Pedido actualizar(Long id, Pedido datos) {
        Pedido pedido = buscarPorId(id);
        pedido.setClienteNombre(datos.getClienteNombre());
        pedido.setClienteEmail(datos.getClienteEmail());
        pedido.setClienteTelefono(datos.getClienteTelefono());
        pedido.setClienteDireccion(datos.getClienteDireccion());
        pedido.setClienteCiudad(datos.getClienteCiudad());
        pedido.setTotal(datos.getTotal());
        return pedidoRepository.save(pedido);
    }

    public void eliminar(Long id) {
        pedidoRepository.deleteById(id);
    }

    private String generarNumeroPedido() {
        String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        long total = pedidoRepository.count() + 1;
        return String.format("VP-%s-%04d", fecha, total);
    }
}