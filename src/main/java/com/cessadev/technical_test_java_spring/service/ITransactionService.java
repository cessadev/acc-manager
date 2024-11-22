package com.cessadev.technical_test_java_spring.service;

import java.math.BigDecimal;

/**
 * ITransactionService defines the business logic for managing account transactions,
 * including deposits, withdrawals, and transfers between accounts.
 */
public interface ITransactionService {

    /**
     * Performs a deposit operation by adding the specified amount to the account balance.
     *
     * @param accountNumber the unique identifier of the account where the deposit will be made.
     * @param amount        the amount to deposit; must be greater than zero.
     * @throws IllegalArgumentException if the amount is invalid or the account does not exist.
     */
    void deposit(String accountNumber, BigDecimal amount);

    /**
     * Performs a withdrawal operation by subtracting the specified amount from the account balance.
     *
     * @param accountNumber the unique identifier of the account where the withdrawal will be made.
     * @param amount        the amount to withdraw; must be greater than zero.
     * @throws IllegalArgumentException if the amount is invalid, exceeds the account balance,
     *                                  or the account does not exist.
     */
    void withdraw(String accountNumber, BigDecimal amount);

    /**
     * Transfers a specified amount from one account to another.
     *
     * @param sourceAccount      the unique identifier of the account from which the amount will be debited.
     * @param destinationAccount the unique identifier of the account to which the amount will be credited.
     * @param amount             the amount to transfer; must be greater than zero.
     * @throws IllegalArgumentException if the amount is invalid, exceeds the source account balance,
     *                                  or either account does not exist.
     */
    void transfer(String sourceAccount, String destinationAccount, BigDecimal amount);
}
