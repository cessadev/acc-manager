package com.cessadev.technical_test_java_spring.model.dto;

import java.math.BigDecimal;

public record CreateAccountDTORequest(
        String ownerName,
        BigDecimal balance
) {
}
