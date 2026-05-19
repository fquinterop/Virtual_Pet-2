package Proyecto.Virtual_Pet.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedidos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroPedido;
    private String clienteNombre;
    private String clienteEmail;
    private String clienteTelefono;
    private String clienteDireccion;
    private String clienteCiudad;

    @Builder.Default
    private String estado = "PENDIENTE";

    @Column(precision = 12, scale = 2)
    private BigDecimal total;

    private String guiaEnvio;
    private String observaciones;

    @Column(updatable = false)
    private LocalDateTime creadoEn;
    private LocalDateTime actualizadoEn;

    @PrePersist
    protected void onCreate() {
        creadoEn = LocalDateTime.now();
        actualizadoEn = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        actualizadoEn = LocalDateTime.now();
    }
}