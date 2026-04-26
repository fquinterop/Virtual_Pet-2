package Proyecto.Virtual_Pet.service;

import Proyecto.Virtual_Pet.model.entity.Cliente;
import Proyecto.Virtual_Pet.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente guardar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public List<Cliente> buscarPorNombre(String nombre) {
        return clienteRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public void eliminar(Long id) {
        clienteRepository.deleteById(id);
    }

    public Cliente actualizar(Long id, Cliente cliente) {
        cliente.setId(id);
        return clienteRepository.save(cliente);
    }
}
