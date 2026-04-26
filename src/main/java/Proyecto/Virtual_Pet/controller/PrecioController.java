package Proyecto.Virtual_Pet.controller;
import Proyecto.Virtual_Pet.service.PrecioService;
import Proyecto.Virtual_Pet.model.entity.Precio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/precio")
public class PrecioController {

    @Autowired
    private PrecioService PrecioService;

    // ✅ Crear Precio
    @PostMapping
    public Precio crearPrecio(@RequestBody Precio precio) {
        return PrecioService.guardar(precio);
    }
//--------------------------------------------
    // ✅ Obtener todos
    @GetMapping
    public List<Precio> obtenerPrecio() {
        return PrecioService.listar();
    }

    // ✅ Obtener por ID
    @GetMapping("/{id}")
    public Precio obtenerPorId(@PathVariable Long id) {
        return PrecioService.buscarPorId(id);
    }

    // ✅ Eliminar
    @DeleteMapping("/{id}")
    public void eliminarPrecio(@PathVariable Long id) {
        PrecioService.eliminar(id);
    }

    // ✅ Actualizar
    @PutMapping("/{id}")
    public Precio actualizarPrecio(@PathVariable Long id, @RequestBody Precio precio) {
        return PrecioService.actualizar(id, precio);
    }
}