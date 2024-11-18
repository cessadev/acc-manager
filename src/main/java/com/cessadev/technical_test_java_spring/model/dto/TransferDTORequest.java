package com.cessadev.technical_test_java_spring.model.dto;

import java.math.BigDecimal;

public record TransferDTORequest(
        String sourceAccount,
        String destinationAccount,
        BigDecimal amount
) {
}
