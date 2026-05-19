package Proyecto.Virtual_Pet.service.admin;

import Proyecto.Virtual_Pet.dto.AdminDTOs.*;
import Proyecto.Virtual_Pet.exception.ResourceNotFoundException;
import Proyecto.Virtual_Pet.model.entity.Cliente;
import Proyecto.Virtual_Pet.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AdminClienteService {

    @Autowired
    private ClienteRepository repo;

    @Transactional(readOnly = true)
    public PageResponse<ClienteResponse> listar(String busqueda, Pageable pageable) {
        Page<Cliente> page = (busqueda != null && !busqueda.isBlank())
                ? repo.findByNombreContainingIgnoreCase(busqueda, pageable)
                : repo.findAll(pageable);

        return PageResponse.<ClienteResponse>builder()
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
    public ClienteResponse obtener(Long id) {
        return toResponse(findById(id));
    }

    public ClienteResponse toggleActivo(Long id) {
        Cliente c = findById(id);
        c.setActivo(!c.getActivo());
        return toResponse(repo.save(c));
    }

    private Cliente findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado: " + id));
    }

    private ClienteResponse toResponse(Cliente c) {
        return ClienteResponse.builder()
                .id(c.getId())
                .nombre(c.getNombre())
                .email(c.getEmail())
                .telefono(c.getTelefono())
                .direccion(c.getDireccion())
                .ciudad(c.getCiudad())
                .activo(c.getActivo())
                .creadoEn(c.getCreadoEn())
                .build();
    }
}