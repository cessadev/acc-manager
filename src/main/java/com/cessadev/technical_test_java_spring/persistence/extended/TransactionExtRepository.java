package com.cessadev.technical_test_java_spring.persistence.extended;

import com.cessadev.technical_test_java_spring.model.TransactionModel;
import com.cessadev.technical_test_java_spring.model.enums.ETypeTransaction;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionExtRepository {

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
  List<TransactionModel> byFilter(String accountNumber, LocalDateTime startDate, LocalDateTime endDate, ETypeTransaction typeTransaction);
}
