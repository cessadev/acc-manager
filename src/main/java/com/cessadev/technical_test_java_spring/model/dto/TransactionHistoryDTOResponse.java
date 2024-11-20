package com.cessadev.technical_test_java_spring.model.dto;

import com.cessadev.technical_test_java_spring.model.enums.ETypeTransaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionHistoryDTOResponse(
        String id,
        ETypeTransaction typeTransaction,
        BigDecimal amount,
        String accountOrigin,
        String accountDestination,
        LocalDateTime date
) {
}
