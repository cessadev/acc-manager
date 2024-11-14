package com.cessadev.technical_test_java_spring.model.dto;

public record ErrorCreateAccountDTO(
        String message,
        String error
) {
}
