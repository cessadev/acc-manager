package com.cessadev.technical_test_java_spring.service;

import com.cessadev.technical_test_java_spring.model.dto.*;
import com.cessadev.technical_test_java_spring.model.enums.ETypeTransaction;

import java.time.LocalDateTime;
import java.util.List;

/**
 * IAccountService defines the contract for account-related business operations.
 * This interface includes methods for:
 * - Retrieving all accounts.
 * - Creating a new account.
 * - Updating an existing account.
 * - Retrieving the status of an account by its account number.
 *
 * Implementations of this interface should contain the business logic for managing accounts.
 */
public interface IAccountService {

    /**
     * Retrieves a list of all accounts.
     *
     * @return a list of AccountDTOResponse objects representing all stored accounts.
     */
    List<AccountDTOResponse> findAllAccounts();

    /**
     * Creates a new account based on the provided account details.
     *
     * @param accountDTORequest a CreateAccountDTORequest object containing the account creation details.
     */
    void createAccount(CreateAccountDTORequest accountDTORequest);

    /**
     * Updates an existing account identified by its account number.
     *
     * @param accountNumber the unique account number of the account to update.
     * @param updateAccountDTORequest an UpdateAccountDTORequest object containing the new account details.
     * @return an UpdateAccountDTOResponse object with the updated account information.
     */
    UpdateAccountDTOResponse updateAccount(String accountNumber, UpdateAccountDTORequest updateAccountDTORequest);

    /**
     * Retrieves the status of an account by its account number.
     *
     * @param accountNumber the unique account number to search for.
     * @return a StatusAccountDTOResponse object representing the current status of the account.
     */
    StatusAccountDTOResponse findStatusAccount(String accountNumber);

    List<TransactionHistoryDTOResponse> getTransactionHistory(
            String accountNumber,
            LocalDateTime startDate,
            LocalDateTime endDate,
            ETypeTransaction type
    );
}
