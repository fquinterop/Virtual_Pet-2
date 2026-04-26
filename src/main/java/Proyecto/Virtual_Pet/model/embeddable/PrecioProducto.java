package Proyecto.Virtual_Pet.model.embeddable;

import jakarta.persistence.Embeddable;

@Embeddable
public class PrecioProducto {

    private double valor;
    private String moneda;

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }
}