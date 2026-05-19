package Proyecto.Virtual_Pet.controller.admin;

import Proyecto.Virtual_Pet.dto.AdminDTOs.*;
import Proyecto.Virtual_Pet.service.admin.AdminPedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/pedidos")
public class PedidoAdminController {

    @Autowired
    private AdminPedidoService service;

    @GetMapping
    public ResponseEntity<PageResponse<PedidoResponse>> listar(
            @RequestParam(required = false) String estado,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "creadoEn") String sort,
            @RequestParam(defaultValue = "DESC") String direction) {

        Sort.Direction dir = Sort.Direction.fromString(direction);
        Pageable pageable = PageRequest.of(page, size, Sort.by(dir, sort));
        return ResponseEntity.ok(service.listar(estado, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponse> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<PedidoResponse> actualizarEstado(
            @PathVariable Long id,
            @Valid @RequestBody ActualizarEstadoPedidoRequest request) {
        return ResponseEntity.ok(service.actualizarEstado(id, request));
    }
}