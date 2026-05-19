package Proyecto.Virtual_Pet.model.entity;

import java.math.BigDecimal;

public record ProductoDTO(
    Long id,
    String nombre,
    String marca,
    String categoria,
    String especie,
    BigDecimal precio,
    Integer stock,
    String disponibilidad
) {}