package com.cessadev.technical_test_java_spring.model.dto;

public record AccountDTOResponse(
        String accountNumber,
        String ownerName,
        String status
) {
}
