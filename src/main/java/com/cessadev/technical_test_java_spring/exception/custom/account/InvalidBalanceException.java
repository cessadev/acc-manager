package com.cessadev.technical_test_java_spring.exception.custom.account;

public class InvalidBalanceException extends RuntimeException {
    public InvalidBalanceException(String message) {
        super(message);
    }
}
