package com.cessadev.technical_test_java_spring.model.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record AccountDTOResponse(
        UUID accountId,
        String accountNumber,
        BigDecimal balance,
        String status,
        String ownerName, // Derivado de UserInfoModel
        String email // Derivado de UserModel

//        String accountNumber,
//        String ownerName,
//        BigDecimal balance,
//        String status
) {
}
