package com.cessadev.technical_test_java_spring.model.dto;

import java.math.BigDecimal;

public record AccountDTOResponse(
        String accountNumber,
        String ownerName,
        BigDecimal balance,
        String status
) {
}
