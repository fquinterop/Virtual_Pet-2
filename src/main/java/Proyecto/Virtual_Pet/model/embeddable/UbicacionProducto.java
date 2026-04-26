package Proyecto.Virtual_Pet.model.embeddable;

import Proyecto.Virtual_Pet.model.enums.TipoUbicacion;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public class UbicacionProducto {

    private String ciudad;
    private String direccion;
    private String sector;

    @Enumerated(EnumType.STRING)
    private TipoUbicacion tipoUbicacion;

    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getSector() { return sector; }
    public void setSector(String sector) { this.sector = sector; }

    public TipoUbicacion getTipoUbicacion() { return tipoUbicacion; }
    public void setTipoUbicacion(TipoUbicacion tipoUbicacion) { this.tipoUbicacion = tipoUbicacion; }
}
