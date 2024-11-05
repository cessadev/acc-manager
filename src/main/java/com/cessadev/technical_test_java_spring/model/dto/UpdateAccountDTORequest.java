package com.cessadev.technical_test_java_spring.model.dto;

import com.cessadev.technical_test_java_spring.model.enums.EStatusAccount;

public record UpdateAccountDTORequest(
        String ownerName,
        EStatusAccount status
        ) {
}
