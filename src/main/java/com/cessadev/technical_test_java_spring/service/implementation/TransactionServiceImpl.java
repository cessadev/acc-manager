package com.cessadev.technical_test_java_spring.service.implementation;

import com.cessadev.technical_test_java_spring.model.AccountModel;
import com.cessadev.technical_test_java_spring.model.TransactionModel;
import com.cessadev.technical_test_java_spring.model.enums.EStatusAccount;
import com.cessadev.technical_test_java_spring.model.enums.ETypeTransaction;
import com.cessadev.technical_test_java_spring.persistence.dao.IAccountDAO;
import com.cessadev.technical_test_java_spring.persistence.dao.ITransactionDAO;
import com.cessadev.technical_test_java_spring.service.ITransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@Slf4j
public class TransactionServiceImpl implements ITransactionService {

  private final IAccountDAO accountDAO;
  private final ITransactionDAO transactionDAO;

  public TransactionServiceImpl(IAccountDAO accountDAO, ITransactionDAO transactionDAO) {
    this.accountDAO = accountDAO;
    this.transactionDAO = transactionDAO;
  }

  /**
   * Deposit a specific amount to an account.
   *
   * @param accountNumber the account number to deposit to.
   * @param amount        the amount to deposit.
   */
  @Override
  @Transactional
  public void deposit(String accountNumber, BigDecimal amount) {
    // Validate input
    if (amount.compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException("Deposit amount must be positive");
    }

    // Fetch account
    Optional<AccountModel> accountOptional = accountDAO.findByAccountNumber(accountNumber);
    if (accountOptional.isEmpty()) {
      throw new IllegalArgumentException("Account not found");
    }

    AccountModel account = accountOptional.get();

    // Update balance
    account.setBalance(account.getBalance().add(amount));
    accountDAO.updateAccount(account);

    // Record transaction
    recordTransaction(ETypeTransaction.DEPOSIT, amount, null, account);
  }

  /**
   * Withdraw a specific amount from an account.
   *
   * @param accountNumber the account number to withdraw from.
   * @param amount        the amount to withdraw.
   */
  @Override
  @Transactional
  public void withdraw(String accountNumber, BigDecimal amount) {
    // Validate input
    if (amount.compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException("Withdrawal amount must be positive");
    }

    // Fetch account
    Optional<AccountModel> accountOptional = accountDAO.findByAccountNumber(accountNumber);
    if (accountOptional.isEmpty()) {
      throw new IllegalArgumentException("Account not found");
    }

    AccountModel account = accountOptional.get();

    // Check sufficient funds
    if (account.getBalance().compareTo(amount) < 0) {
      throw new IllegalArgumentException("Insufficient funds");
    }

    // Update balance
    account.setBalance(account.getBalance().subtract(amount));
    accountDAO.updateAccount(account);

    // Persist transaction
    recordTransaction(ETypeTransaction.WITHDRAW, amount, account, null);
  }

  /**
   * Transfer a specific amount between two accounts.
   *
   * @param accountOrigin      the account number to transfer from.
   * @param accountDestination the account number to transfer to.
   * @param amount             the amount to transfer.
   */
  @Override
  @Transactional
  public void transfer(String accountOrigin, String accountDestination, BigDecimal amount) {
    // Validate input
    if (amount.compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException("Transfer amount must be positive");
    }

    // Fetch source account
    Optional<AccountModel> sourceOptional = accountDAO.findByAccountNumber(accountOrigin);
    if (sourceOptional.isEmpty()) {
      throw new IllegalArgumentException("Source account not found");
    }

    AccountModel source = sourceOptional.get();

    // Fetch destination account
    Optional<AccountModel> destinationOptional = accountDAO.findByAccountNumber(accountDestination);
    if (destinationOptional.isEmpty()) {
      throw new IllegalArgumentException("Destination account not found");
    }

    AccountModel destination = destinationOptional.get();

    // Validate both accounts are active
    if (!source.getStatus().equals(EStatusAccount.ACTIVE) || !destination.getStatus().equals(EStatusAccount.ACTIVE)) {
      throw new IllegalArgumentException("Both accounts must be active to perform a transfer");
    }

    // Check sufficient funds in source account
    if (source.getBalance().compareTo(amount) < 0) {
      throw new IllegalArgumentException("Insufficient funds in source account");
    }

    // Update balances
    source.setBalance(source.getBalance().subtract(amount));
    destination.setBalance(destination.getBalance().add(amount));

    // Persist changes
    accountDAO.updateAccount(source);
    accountDAO.updateAccount(destination);

    // Persist transaction
    recordTransaction(ETypeTransaction.TRANSFER, amount, source, destination);
  }

  private void recordTransaction(
          ETypeTransaction typeTransaction,
          BigDecimal amount,
          AccountModel accountOrigin,
          AccountModel accountDestination
  ) {
    // Create instance of TransactionModel
    TransactionModel transaction = new TransactionModel();
    transaction.setTypeTransaction(typeTransaction);
    transaction.setAmount(amount);
    transaction.setAccountOrigin(accountOrigin);
    transaction.setAccountDestination(accountDestination);

    // Persist transaction
    transactionDAO.insertTransaction(transaction);
  }
}
