package Proyecto.Virtual_Pet.controller;

import Proyecto.Virtual_Pet.model.entity.Pedido;
import Proyecto.Virtual_Pet.model.enums.EstadoPedido;
import Proyecto.Virtual_Pet.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    // ✅ Crear pedido
    @PostMapping
    public Pedido crearPedido(@RequestBody Pedido pedido) {
        return pedidoService.guardar(pedido);
    }

    // ✅ Obtener todos
    @GetMapping
    public List<Pedido> obtenerPedidos() {
        return pedidoService.listar();
    }

    // ✅ Obtener por ID
    @GetMapping("/{id}")
    public Pedido obtenerPorId(@PathVariable Long id) {
        return pedidoService.buscarPorId(id);
    }

    // ✅ Pedidos de un cliente
    @GetMapping("/cliente/{clienteId}")
    public List<Pedido> pedidosPorCliente(@PathVariable Long clienteId) {
        return pedidoService.buscarPorCliente(clienteId);
    }

    // ✅ Pedidos por estado
    @GetMapping("/estado/{estado}")
    public List<Pedido> pedidosPorEstado(@PathVariable EstadoPedido estado) {
        return pedidoService.buscarPorEstado(estado);
    }

    // ✅ Cambiar estado del pedido
    @PatchMapping("/{id}/estado")
    public Pedido cambiarEstado(@PathVariable Long id, @RequestParam EstadoPedido estado) {
        return pedidoService.cambiarEstado(id, estado);
    }

    // ✅ Actualizar pedido completo
    @PutMapping("/{id}")
    public Pedido actualizarPedido(@PathVariable Long id, @RequestBody Pedido pedido) {
        return pedidoService.actualizar(id, pedido);
    }

    // ✅ Eliminar
    @DeleteMapping("/{id}")
    public void eliminarPedido(@PathVariable Long id) {
        pedidoService.eliminar(id);
    }
}
