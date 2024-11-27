package com.cessadev.technical_test_java_spring.persistence.dao.implementation;

import com.cessadev.technical_test_java_spring.model.TransactionModel;
import com.cessadev.technical_test_java_spring.model.enums.ETypeTransaction;
import com.cessadev.technical_test_java_spring.persistence.dao.ITransactionDAO;
import com.cessadev.technical_test_java_spring.persistence.repository.ITransactionRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * TransactionDAOImpl is the implementation of the ITransactionDAO interface.
 * This class interacts with the database through the ITransactionRepository
 * to perform data access operations for transaction management.
 * <p>
 * It provides the logic for:
 * - Inserting a transaction into the database.
 * - Retrieving transactions based on filters such as account number, date range, and type.
 */
@Component
public class TransactionDAOImpl implements ITransactionDAO {

  private final ITransactionRepository transactionRepository;

  /**
   * Constructor for TransactionDAOImpl.
   *
   * @param transactionRepository the repository for performing transaction-related database operations.
   */
  public TransactionDAOImpl(ITransactionRepository transactionRepository) {
    this.transactionRepository = transactionRepository;
  }

  /**
   * Saves a new transaction in the database.
   *
   * @param transactionModel the TransactionModel object containing the details of the transaction to insert.
   */
  @Override
  public void insertTransaction(TransactionModel transactionModel) {
    transactionRepository.save(transactionModel);
  }

  /**
   * Retrieves a list of transactions filtered by account number, date range, and transaction type.
   *
   * @param accountNumber   the account number to filter transactions by.
   * @param startDate       the start date for filtering transactions.
   * @param endDate         the end date for filtering transactions.
   * @param typeTransaction the type of transaction to filter by (e.g., DEPOSIT, WITHDRAWAL, TRANSFER).
   * @return a list of TransactionModel objects matching the specified filters.
   */
  @Override
  public List<TransactionModel> findByFilters(String accountNumber, LocalDateTime startDate, LocalDateTime endDate, ETypeTransaction typeTransaction) {
    return transactionRepository.byFilter(accountNumber, startDate, endDate, typeTransaction);
  }
}
