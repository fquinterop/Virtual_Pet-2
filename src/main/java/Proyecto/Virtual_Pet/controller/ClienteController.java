package Proyecto.Virtual_Pet.controller;

import Proyecto.Virtual_Pet.model.entity.Cliente;
import Proyecto.Virtual_Pet.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // ✅ Crear cliente
    @PostMapping
    public Cliente crearCliente(@RequestBody Cliente cliente) {
        return clienteService.guardar(cliente);
    }

    // ✅ Obtener todos
    @GetMapping
    public List<Cliente> obtenerClientes() {
        return clienteService.listar();
    }

    // ✅ Obtener por ID
    @GetMapping("/{id}")
    public Cliente obtenerPorId(@PathVariable Long id) {
        return clienteService.buscarPorId(id);
    }

    // ✅ Buscar por nombre
    @GetMapping("/buscar")
    public List<Cliente> buscarPorNombre(@RequestParam String nombre) {
        return clienteService.buscarPorNombre(nombre);
    }

    // ✅ Actualizar
    @PutMapping("/{id}")
    public Cliente actualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        return clienteService.actualizar(id, cliente);
    }

    // ✅ Eliminar
    @DeleteMapping("/{id}")
    public void eliminarCliente(@PathVariable Long id) {
        clienteService.eliminar(id);
    }
}
