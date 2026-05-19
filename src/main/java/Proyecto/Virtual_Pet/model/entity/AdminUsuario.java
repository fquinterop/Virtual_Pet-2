package Proyecto.Virtual_Pet.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "admin_usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nombre;

    private String email;

    // "SUPER_ADMIN", "ADMIN", "OPERADOR"
    @Column(nullable = false)
    @Builder.Default
    private String rol = "OPERADOR";

    @Builder.Default
    private Boolean activo = true;

    @Column(updatable = false)
    private LocalDateTime creadoEn;

    @PrePersist
    protected void onCreate() {
        creadoEn = LocalDateTime.now();
    }
}