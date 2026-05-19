package Proyecto.Virtual_Pet.service.admin;

import Proyecto.Virtual_Pet.dto.AdminDTOs.*;
import Proyecto.Virtual_Pet.exception.ResourceNotFoundException;
import Proyecto.Virtual_Pet.model.entity.Pedido;
import Proyecto.Virtual_Pet.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AdminPedidoService {

    @Autowired
    private PedidoRepository pedidoRepo;

    @Transactional(readOnly = true)
    public PageResponse<PedidoResponse> listar(String estado, Pageable pageable) {
        Page<Pedido> page = (estado != null && !estado.isBlank())
                ? pedidoRepo.findByEstado(estado, pageable)
                : pedidoRepo.findAll(pageable);

        return PageResponse.<PedidoResponse>builder()
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
    public PedidoResponse obtener(Long id) {
        return toResponse(findById(id));
    }

    public PedidoResponse actualizarEstado(Long id, ActualizarEstadoPedidoRequest request) {
        Pedido pedido = findById(id);
        pedido.setEstado(request.getEstado());
        if (request.getGuiaEnvio() != null) pedido.setGuiaEnvio(request.getGuiaEnvio());
        if (request.getObservaciones() != null) pedido.setObservaciones(request.getObservaciones());
        return toResponse(pedidoRepo.save(pedido));
    }

    private Pedido findById(Long id) {
        return pedidoRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado: " + id));
    }

    private PedidoResponse toResponse(Pedido p) {
        return PedidoResponse.builder()
                .id(p.getId())
                .numeroPedido(p.getNumeroPedido())
                .clienteNombre(p.getClienteNombre())
                .clienteEmail(p.getClienteEmail())
                .clienteTelefono(p.getClienteTelefono())
                .clienteDireccion(p.getClienteDireccion())
                .clienteCiudad(p.getClienteCiudad())
                .estado(p.getEstado())
                .total(p.getTotal())
                .guiaEnvio(p.getGuiaEnvio())
                .observaciones(p.getObservaciones())
                .creadoEn(p.getCreadoEn())
                .actualizadoEn(p.getActualizadoEn())
                .build();
    }
}