package com.cessadev.technical_test_java_spring.util.account;

import com.cessadev.technical_test_java_spring.exception.custom.InvalidBalanceException;

import java.math.BigDecimal;

public class ValidateInitialBalance {

    /**
     * Validates that the initial balance is not negative.
     *
     * @param balance the initial balance to validate.
     * @throws InvalidBalanceException if the balance is negative.
     */
    public static void validateInitialBalance(BigDecimal balance) {
        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidBalanceException("Initial balance cannot be negative");
        }
    }
}
