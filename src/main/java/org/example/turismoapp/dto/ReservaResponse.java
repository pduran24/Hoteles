package org.example.turismoapp.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ReservaResponse(
        Long id,
        String hotelNombre,
        String clienteNombre,
        LocalDate fechaEntrada,
        LocalDate fechaSalida,
        Integer numeroDias,
        BigDecimal precioTotal
) {
}
