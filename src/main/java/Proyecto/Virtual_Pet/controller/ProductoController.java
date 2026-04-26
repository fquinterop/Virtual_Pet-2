package Proyecto.Virtual_Pet.controller;
import Proyecto.Virtual_Pet.model.entity.Producto;
import Proyecto.Virtual_Pet.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // ✅ Crear producto
    @PostMapping
    public Producto crearProducto(@RequestBody Producto producto) {
        return productoService.guardar(producto);
    }

    // ✅ Obtener todos
    @GetMapping
    public List<Producto> obtenerProductos() {
        return productoService.listar();
    }

    // ✅ Obtener por ID
    @GetMapping("/{id}")
    public Producto obtenerPorId(@PathVariable Long id) {
        return productoService.buscarPorId(id);
    }

    // ✅ Eliminar
    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable Long id) {
        productoService.eliminar(id);
    }

    // ✅ Actualizar
    @PutMapping("/{id}")
    public Producto actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        return productoService.actualizar(id, producto);
    }
}