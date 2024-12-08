package com.cessadev.technical_test_java_spring.model.dto;

import com.cessadev.technical_test_java_spring.model.enums.EEmploymentStatus;
import com.cessadev.technical_test_java_spring.model.enums.EGender;
import com.cessadev.technical_test_java_spring.model.enums.EMaritalStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

public record CreateUserCompleteDTORequest(
        String email,
        String password,
        Set<String> roles,
        String firstName,
        String lastName,
        String middleName,
        LocalDate dateOfBirth,
        EGender gender,
        String phoneNumber,
        String alternatePhoneNumber,
        String address,
        String city,
        String state,
        String postalCode,
        String country,
        EEmploymentStatus employmentStatus,
        String jobTitle,
        String companyName,
        String industry,
        BigDecimal annualIncome,
        Integer creditScore,
        String preferredCurrency,
        EMaritalStatus maritalStatus,
        Integer numberOfDependents,
        String profilePictureUrl
) {
}
