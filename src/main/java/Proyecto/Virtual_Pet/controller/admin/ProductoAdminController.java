package Proyecto.Virtual_Pet.controller.admin;

import Proyecto.Virtual_Pet.dto.AdminDTOs.*;
import Proyecto.Virtual_Pet.service.admin.AdminProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/productos")
public class ProductoAdminController {

    @Autowired
    private AdminProductoService service;

    @GetMapping
    public ResponseEntity<PageResponse<ProductoResponse>> listar(
            @RequestParam(required = false) String busqueda,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String especie,
            @RequestParam(required = false) Boolean activo,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sort) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return ResponseEntity.ok(service.listar(busqueda, categoria, especie, activo, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponse> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @PostMapping
    public ResponseEntity<ProductoResponse> crear(@Valid @RequestBody ProductoRequest request) {
        return ResponseEntity.ok(service.crear(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponse> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ProductoRequest request) {
        return ResponseEntity.ok(service.actualizar(id, request));
    }

    @PatchMapping("/{id}/precio")
    public ResponseEntity<ProductoResponse> actualizarPrecio(
            @PathVariable Long id,
            @RequestBody ActualizarPrecioRequest request) {
        return ResponseEntity.ok(service.actualizarPrecio(id, request));
    }

    @PatchMapping("/{id}/stock")
    public ResponseEntity<ProductoResponse> actualizarStock(
            @PathVariable Long id,
            @RequestBody ActualizarStockRequest request) {
        return ResponseEntity.ok(service.actualizarStock(id, request));
    }

    @PatchMapping("/{id}/toggle")
    public ResponseEntity<ProductoResponse> toggleActivo(@PathVariable Long id) {
        return ResponseEntity.ok(service.toggleActivo(id));
    }
}