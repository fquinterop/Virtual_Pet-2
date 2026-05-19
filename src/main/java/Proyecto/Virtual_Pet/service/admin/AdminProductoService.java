package Proyecto.Virtual_Pet.service.admin;

import Proyecto.Virtual_Pet.dto.AdminDTOs.*;
import Proyecto.Virtual_Pet.exception.ResourceNotFoundException;
import Proyecto.Virtual_Pet.model.entity.Producto;
import Proyecto.Virtual_Pet.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AdminProductoService {

    @Autowired
    private ProductoRepository repo;

    @Transactional(readOnly = true)
    public PageResponse<ProductoResponse> listar(String busqueda, String categoria,
                                                  String especie, Boolean activo,
                                                  Pageable pageable) {
        Page<Producto> page = repo.findAll(pageable);
        return PageResponse.<ProductoResponse>builder()
                .contenido(page.getContent().stream()
                        .map(this::toResponse)
                        .collect(Collectors.toList()))
                .paginaActual(page.getNumber())
                .totalPaginas(page.getTotalPages())
                .totalElementos(page.getTotalElements())
                .tamanioPagina(page.getSize())
                .esUltima(page.isLast())
                .build();
    }

    @Transactional(readOnly = true)
    public ProductoResponse obtener(Long id) {
        return toResponse(findById(id));
    }

    public ProductoResponse crear(ProductoRequest request) {
        Producto p = new Producto();
        p.setNombre(request.getNombre());
        p.setDescripcion(request.getDescripcion());
        p.setMarca(request.getMarca());
        p.setCategoria(request.getCategoria());
        p.setEspecie(request.getEspecie());
        p.setPrecio(request.getPrecio());
        p.setStock(request.getStock());
        p.setPresentaciones(request.getPresentaciones());
        p.setReferencia(request.getReferencia());
        p.setImagenUrl(request.getImagenUrl());
        p.setActivo(request.getActivo() != null ? request.getActivo() : true);
        p.setDisponibilidad(request.getDisponibilidad());
        return toResponse(repo.save(p));
    }

    public ProductoResponse actualizar(Long id, ProductoRequest request) {
        Producto p = findById(id);
        p.setNombre(request.getNombre());
        p.setDescripcion(request.getDescripcion());
        p.setMarca(request.getMarca());
        p.setCategoria(request.getCategoria());
        p.setEspecie(request.getEspecie());
        p.setPrecio(request.getPrecio());
        p.setStock(request.getStock());
        p.setPresentaciones(request.getPresentaciones());
        p.setReferencia(request.getReferencia());
        p.setImagenUrl(request.getImagenUrl());
        if (request.getActivo() != null) p.setActivo(request.getActivo());
        if (request.getDisponibilidad() != null) p.setDisponibilidad(request.getDisponibilidad());
        return toResponse(repo.save(p));
    }

    public ProductoResponse actualizarPrecio(Long id, ActualizarPrecioRequest request) {
        Producto p = findById(id);
        p.setPrecio(request.getPrecio());
        return toResponse(repo.save(p));
    }

    public ProductoResponse actualizarStock(Long id, ActualizarStockRequest request) {
        Producto p = findById(id);
        p.setStock(request.getStock());
        if (request.getDisponibilidad() != null) p.setDisponibilidad(request.getDisponibilidad());
        return toResponse(repo.save(p));
    }

    public ProductoResponse toggleActivo(Long id) {
        Producto p = findById(id);
        p.setActivo(!p.getActivo());
        return toResponse(repo.save(p));
    }

    private Producto findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado: " + id));
    }

    private ProductoResponse toResponse(Producto p) {
        return ProductoResponse.builder()
                .id(p.getId())
                .nombre(p.getNombre())
                .descripcion(p.getDescripcion())
                .marca(p.getMarca())
                .categoria(p.getCategoria())
                .especie(p.getEspecie())
                .precio(p.getPrecio())
                .stock(p.getStock())
                .presentaciones(p.getPresentaciones())
                .referencia(p.getReferencia())
                .imagenUrl(p.getImagenUrl())
                .activo(p.getActivo())
                .disponibilidad(p.getDisponibilidad())
                .creadoEn(p.getCreadoEn())
                .actualizadoEn(p.getActualizadoEn())
                .build();
    }
}