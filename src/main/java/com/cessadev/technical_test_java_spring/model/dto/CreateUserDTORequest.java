package com.cessadev.technical_test_java_spring.model.dto;

import java.util.Set;

public record CreateUserDTORequest(
        String email,
        String password,
        Set<String> roles
) {
}
