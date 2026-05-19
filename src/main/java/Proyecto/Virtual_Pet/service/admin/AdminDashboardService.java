package Proyecto.Virtual_Pet.service.admin;

import Proyecto.Virtual_Pet.dto.AdminDTOs.*;
import Proyecto.Virtual_Pet.repository.PedidoRepository;
import Proyecto.Virtual_Pet.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class AdminDashboardService {

    @Autowired
    private ProductoRepository productoRepo;

    @Autowired
    private PedidoRepository pedidoRepo;

    @Autowired
    private AdminProductoService productoService;

    @Autowired
    private AdminPedidoService pedidoService;

    public DashboardStats getStats() {

        // Total productos
        long totalProductos = productoRepo.count();

        // Total pedidos
        long totalPedidos = pedidoRepo.count();

        // Últimos 5 pedidos
        Page<Proyecto.Virtual_Pet.model.entity.Pedido> ultimosPedidos = pedidoRepo.findAll(
                PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "id"))
        );

        // Productos con stock bajo (menos de 5)
        List<Proyecto.Virtual_Pet.model.entity.Producto> stockBajo =
                productoRepo.findAll().stream()
                        .filter(p -> p.getStock() != null && p.getStock() < 5)
                        .collect(Collectors.toList());

        return DashboardStats.builder()
                .pedidosHoy(totalPedidos)
                .ventasHoy(BigDecimal.ZERO)
                .productosStockBajo(stockBajo.size())
                .totalProductosActivos(totalProductos)
                .pedidosPendientes(0)
                .pedidosEnCamino(0)
                .productosStockBajoLista(
                        stockBajo.stream()
                                .map(p -> productoService.obtener(p.getId()))
                                .collect(Collectors.toList())
                )
                .ultimosPedidos(
                        ultimosPedidos.getContent().stream()
                                .map(p -> pedidoService.obtener(p.getId()))
                                .collect(Collectors.toList())
                )
                .build();
    }
}