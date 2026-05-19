package Proyecto.Virtual_Pet.dto;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class AdminDTOs {

    @Data
    public static class LoginRequest {
        @NotBlank(message = "Username requerido")
        private String username;
        @NotBlank(message = "Password requerido")
        private String password;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginResponse {
        private String token;
        private String username;
        private String nombre;
        private String rol;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductoResponse {
        private Long id;
        private String nombre;
        private String descripcion;
        private String marca;
        private String categoria;
        private String especie;
        private BigDecimal precio;
        private Integer stock;
        private String presentaciones;
        private String referencia;
        private String imagenUrl;
        private Boolean activo;
        private String disponibilidad;
        private LocalDateTime creadoEn;
        private LocalDateTime actualizadoEn;
    }

    @Data
    public static class ProductoRequest {
        @NotBlank(message = "Nombre requerido")
        private String nombre;
        private String descripcion;
        @NotBlank(message = "Marca requerida")
        private String marca;
        @NotBlank(message = "Categoría requerida")
        private String categoria;
        private String especie;
        @NotNull(message = "Precio requerido")
        @DecimalMin(value = "0.0", message = "Precio no puede ser negativo")
        private BigDecimal precio;
        @Min(value = 0)
        private Integer stock;
        private String presentaciones;
        private String referencia;
        private String imagenUrl;
        private Boolean activo;
        private String disponibilidad;
    }

    @Data
    public static class ActualizarPrecioRequest {
        @NotNull
        private BigDecimal precio;
    }

    @Data
    public static class ActualizarStockRequest {
        @NotNull
        @Min(0)
        private Integer stock;
        private String disponibilidad;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PedidoResponse {
        private Long id;
        private String numeroPedido;
        private String clienteNombre;
        private String clienteEmail;
        private String clienteTelefono;
        private String clienteDireccion;
        private String clienteCiudad;
        private String estado;
        private BigDecimal total;
        private String guiaEnvio;
        private String observaciones;
        private List<DetallePedidoResponse> detalles;
        private LocalDateTime creadoEn;
        private LocalDateTime actualizadoEn;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DetallePedidoResponse {
        private Long id;
        private Long productoId;
        private String productoNombre;
        private String productoMarca;
        private Integer cantidad;
        private BigDecimal precioUnitario;
        private BigDecimal subtotal;
    }

    @Data
    public static class ActualizarEstadoPedidoRequest {
        @NotBlank(message = "Estado requerido")
        private String estado;
        private String guiaEnvio;
        private String observaciones;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DashboardStats {
        private long pedidosHoy;
        private BigDecimal ventasHoy;
        private long productosStockBajo;
        private long totalProductosActivos;
        private long pedidosPendientes;
        private long pedidosEnCamino;
        private List<ProductoResponse> productosStockBajoLista;
        private List<PedidoResponse> ultimosPedidos;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PageResponse<T> {
        private List<T> contenido;
        private int paginaActual;
        private int totalPaginas;
        private long totalElementos;
        private int tamanioPagina;
        private boolean esUltima;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ClienteResponse {
        private Long id;
        private String nombre;
        private String email;
        private String telefono;
        private String direccion;
        private String ciudad;
        private Boolean activo;
        private LocalDateTime creadoEn;
    }

    @Data
    public static class ClienteRequest {
        @NotBlank(message = "Nombre requerido")
        private String nombre;
        @Email(message = "Email inválido")
        @NotBlank(message = "Email requerido")
        private String email;
        private String telefono;
        private String direccion;
        private String ciudad;
    }
}