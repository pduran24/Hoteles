package org.example.turismoapp.dto;

public record ErrorResponseDTO(
        String error,
        String message,
        Integer status
) {
}
