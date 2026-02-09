package org.example.turismoapp.dto;

import java.math.BigDecimal;

public record HotelResponse(
        Long id,
        String nombre,
        String ubicacion,
        String descripcion,
        Integer estrellas,
        BigDecimal precioNoche
) {
}
