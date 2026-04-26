package Proyecto.Virtual_Pet.model.entity;

import Proyecto.Virtual_Pet.model.embeddable.PrecioProducto;
import Proyecto.Virtual_Pet.model.embeddable.UbicacionProducto;
import jakarta.persistence.*;

@Entity
public class Precio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Embedded
    private UbicacionProducto ubicacion;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "valor", column = @Column(name = "precio_valor")),
        @AttributeOverride(name = "moneda", column = @Column(name = "precio_moneda"))
    })
    private PrecioProducto precio;

    // GETTERS Y SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public UbicacionProducto getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(UbicacionProducto ubicacion) {
        this.ubicacion = ubicacion;
    }

    public PrecioProducto getPrecio() {
        return precio;
    }

    public void setPrecio(PrecioProducto precio) {
        this.precio = precio;
    }
}
