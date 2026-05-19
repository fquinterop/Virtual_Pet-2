package Proyecto.Virtual_Pet.controller.admin;

import Proyecto.Virtual_Pet.dto.AdminDTOs.*;
import Proyecto.Virtual_Pet.service.admin.AdminClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/clientes")
public class ClienteAdminController {

    @Autowired
    private AdminClienteService service;

    @GetMapping
    public ResponseEntity<PageResponse<ClienteResponse>> listar(
            @RequestParam(required = false) String busqueda,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "creadoEn") String sort,
            @RequestParam(defaultValue = "DESC") String direction) {

        Sort.Direction dir = Sort.Direction.fromString(direction);
        Pageable pageable = PageRequest.of(page, size, Sort.by(dir, sort));
        return ResponseEntity.ok(service.listar(busqueda, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @PatchMapping("/{id}/toggle")
    public ResponseEntity<ClienteResponse> toggleActivo(@PathVariable Long id) {
        return ResponseEntity.ok(service.toggleActivo(id));
    }
}