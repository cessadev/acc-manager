package com.cessadev.technical_test_java_spring.model.dto;

public record UserWithInfoDTOResponse(
        Long id,
        String email,
        String firstName,
        String lastName,
        String address,
        String phoneNumber,
        String city
) {
}
