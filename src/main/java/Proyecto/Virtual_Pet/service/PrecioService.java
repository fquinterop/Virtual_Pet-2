package Proyecto.Virtual_Pet.service;

import Proyecto.Virtual_Pet.model.entity.Precio;
import Proyecto.Virtual_Pet.repository.PrecioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrecioService {

    @Autowired
    private PrecioRepository precioRepository;

    public Precio guardar(Precio precio) {
        return precioRepository.save(precio);
    }

    public List<Precio> listar() {
        return precioRepository.findAll();
    }

    public Precio buscarPorId(Long id) {
        return precioRepository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        precioRepository.deleteById(id);
    }

    public Precio actualizar(Long id, Precio precio) {
        precio.setId(id);
        return precioRepository.save(precio);
    }
}