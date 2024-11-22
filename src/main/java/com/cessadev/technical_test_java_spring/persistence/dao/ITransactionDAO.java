package com.cessadev.technical_test_java_spring.persistence.dao;

import com.cessadev.technical_test_java_spring.model.TransactionModel;
import com.cessadev.technical_test_java_spring.model.enums.ETypeTransaction;

import java.time.LocalDateTime;
import java.util.List;

/**
 * ITransactionDAO defines the data access operations for managing transactions.
 * It provides methods to:
 * - Insert a new transaction into the database.
 * - Retrieve transactions based on filtering criteria.
 *
 * Implementations of this interface are responsible for interacting with the underlying
 * database or data source to perform these operations.
 */
public interface ITransactionDAO {

    /**
     * Inserts a new transaction into the database.
     *
     * @param transactionModel the TransactionModel object containing the details of the transaction to insert.
     */
    void insertTransaction(TransactionModel transactionModel);

    /**
     * Retrieves a list of transactions filtered by the provided criteria.
     *
     * @param accountNumber   the account number to filter transactions by.
     * @param startDate       the start date for filtering transactions.
     * @param endDate         the end date for filtering transactions.
     * @param typeTransaction the type of transaction to filter by, such as DEPOSIT, WITHDRAWAL, or TRANSFER.
     * @return a list of TransactionModel objects that match the filtering criteria.
     */
    List<TransactionModel> findByFilters(String accountNumber, LocalDateTime startDate, LocalDateTime endDate, ETypeTransaction typeTransaction);
}
