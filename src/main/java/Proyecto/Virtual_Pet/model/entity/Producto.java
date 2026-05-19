package Proyecto.Virtual_Pet.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private String marca;
    private String categoria;

    @Builder.Default
    private String especie = "AMBOS";

    @Column(precision = 12, scale = 2)
    private BigDecimal precio;

    @Builder.Default
    private Integer stock = 0;

    private String presentaciones;
    private String referencia;
    private String imagenUrl;

    @Builder.Default
    private Boolean activo = true;

    @Builder.Default
    private String disponibilidad = "DISPONIBLE";

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