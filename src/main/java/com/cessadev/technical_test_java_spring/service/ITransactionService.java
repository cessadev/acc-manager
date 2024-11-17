package com.cessadev.technical_test_java_spring.service;

import java.math.BigDecimal;

public interface ITransactionService {

    void deposit(String accountNumber, BigDecimal amount);

    void withdraw(String accountNumber, BigDecimal amount);

    void transfer(String sourceAccount, String destinationAccount, BigDecimal amount);
}
