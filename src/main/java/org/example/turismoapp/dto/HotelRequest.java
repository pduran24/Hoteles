package org.example.turismoapp.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record HotelRequest(

        @NotBlank(message = "El nombre del hotel es obligatorio")
        String nombre,

        @NotBlank(message = "La ubicación del hotel es obligatoria")
        String ubicacion,

        String descripcion,

        @NotNull(message = "La cantidad de estrellas es obligatoria")
        @Min(1) @Max(5)
        Integer estrellas,

        @NotNull(message = "El precio por noche es obligatorio")
        @DecimalMin(value = "10.0", inclusive = false, message = "El precio por noche debe ser mayor a 10")
        BigDecimal precioNoche
) {
}
