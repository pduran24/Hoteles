package org.example.turismoapp.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ReservaRequest(
        @NotNull(message = "Debes indicar el cliente")
        Long clienteId,

        @NotNull(message = "Debes indicar el hotel")
        Long hotelId,

        @NotNull(message = "La fecha de entrada es obligatoria")
        @Future(message = "La reserva debe ser para una fecha futura") // Validación Pro
        LocalDate fechaEntrada,

        @NotNull(message = "La fecha de salida es obligatoria")
        @Future(message = "La fecha de salida debe ser futura")
        LocalDate fechaSalida
) {
}
