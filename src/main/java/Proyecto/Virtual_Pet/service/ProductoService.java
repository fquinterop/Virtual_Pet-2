package Proyecto.Virtual_Pet.service;

import Proyecto.Virtual_Pet.model.entity.Producto;
import Proyecto.Virtual_Pet.model.entity.ProductoDTO;
import Proyecto.Virtual_Pet.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }

    public List<Producto> listar() {
        return productoRepository.findAll();
    }

    public Producto buscarPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }

    public Producto actualizar(Long id, Producto producto) {
        producto.setId(id);
        return productoRepository.save(producto);
    }

    // ✅ Nuevo método DTO — devuelve solo los campos necesarios
    public List<ProductoDTO> listarDTO() {
        return productoRepository.findAll().stream()
                .map(p -> new ProductoDTO(
                        p.getId(),
                        p.getNombre(),
                        p.getMarca(),
                        p.getCategoria(),
                        p.getEspecie(),
                        p.getPrecio(),
                        p.getStock(),
                        p.getDisponibilidad()
                ))
                .toList();
    }
}