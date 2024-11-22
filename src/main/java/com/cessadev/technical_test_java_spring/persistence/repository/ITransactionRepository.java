package com.cessadev.technical_test_java_spring.persistence.repository;

import com.cessadev.technical_test_java_spring.model.TransactionModel;
import com.cessadev.technical_test_java_spring.model.enums.ETypeTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * ITransactionRepository provides the data access layer for managing TransactionModel entities.
 * It extends JpaRepository, inheriting common CRUD operations, and defines custom queries
 * for advanced filtering of transactions.
 */
@Repository
public interface ITransactionRepository extends JpaRepository<TransactionModel, UUID> {

    /**
     * Retrieves a list of transactions filtered by account number, date range, and transaction type.
     * This query uses optional filters:
     * - Account number can match either the origin or destination account.
     * - Start date and end date define the range of transaction dates.
     * - Transaction type filters by the type (e.g., DEPOSIT, WITHDRAWAL, TRANSFER).
     *
     * @param accountNumber   the account number to filter by; can match either origin or destination.
     * @param startDate       the start date for the transaction filter (inclusive).
     * @param endDate         the end date for the transaction filter (inclusive).
     * @param typeTransaction the type of transaction to filter by.
     * @return a list of TransactionModel objects matching the specified filters.
     */
    @Query("SELECT t FROM TransactionModel t " +
            "LEFT JOIN t.accountOrigin ao " +
            "LEFT JOIN t.accountDestination ad " +
            "WHERE (:accountNumber IS NULL OR " +
            "       (ao.accountNumber = :accountNumber OR ad.accountNumber = :accountNumber)) " +
            "AND (:startDate IS NULL OR t.date >= :startDate) " +
            "AND (:endDate IS NULL OR t.date <= :endDate) " +
            "AND (:typeTransaction IS NULL OR t.typeTransaction = :typeTransaction)")
    List<TransactionModel> findByFilters(String accountNumber, LocalDateTime startDate, LocalDateTime endDate, ETypeTransaction typeTransaction);
}
