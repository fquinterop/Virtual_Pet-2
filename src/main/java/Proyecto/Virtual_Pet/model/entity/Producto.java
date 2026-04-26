package Proyecto.Virtual_Pet.model.entity;

import Proyecto.Virtual_Pet.model.embeddable.UbicacionProducto;
import jakarta.persistence.*;

@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "ciudad",        column = @Column(name = "ubicacion_ciudad")),
        @AttributeOverride(name = "direccion",     column = @Column(name = "ubicacion_direccion")),
        @AttributeOverride(name = "sector",        column = @Column(name = "ubicacion_sector")),
        @AttributeOverride(name = "tipoUbicacion", column = @Column(name = "ubicacion_tipo"))
    })
    private UbicacionProducto ubicacion;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public UbicacionProducto getUbicacion() { return ubicacion; }
    public void setUbicacion(UbicacionProducto ubicacion) { this.ubicacion = ubicacion; }
}
