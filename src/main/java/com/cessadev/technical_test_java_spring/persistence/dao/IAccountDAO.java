package com.cessadev.technical_test_java_spring.persistence.dao;

import com.cessadev.technical_test_java_spring.model.AccountModel;
import com.cessadev.technical_test_java_spring.model.enums.EStatusAccount;

import java.util.List;
import java.util.Optional;

/**
 * IAccountDAO defines the contract for data access operations related to accounts.
 * This interface provides methods to:
 * - Retrieve a list of all accounts.
 * - Create new accounts.
 * - Find and verify accounts by account number.
 * - Retrieve the status of an account by its account number.
 *
 * Implementations of this interface should handle the actual interaction with the database.
 */
public interface IAccountDAO {

    /**
     * Retrieves all accounts stored in the database.
     *
     * @return a list of all AccountModel entities in the database.
     */
    List<AccountModel> findAllAccounts();

    /**
     * Saves a new account to the database.
     *
     * @param accountModel the account model to be saved.
     */
    void createAccount(AccountModel accountModel);

    /**
     * Update an account in the database.
     *
     * @param accountModel the account model to be saved.
     */
    void updateAccount(AccountModel accountModel);

    /**
     * Update an account in the database and returns the saved entity.
     *
     * @param accountModel the account model to be saved.
     * @return the saved AccountModel entity.
     */
    AccountModel updateAccountWithReturn(AccountModel accountModel);

    /**
     * Finds an account by its unique account number.
     *
     * @param accountNumber the unique account number to search for.
     * @return an Optional containing the AccountModel if found, or empty if not found.
     */
    Optional<AccountModel> findByAccountNumber(String accountNumber);

    /**
     * Retrieves the status of an account by its account number.
     *
     * @param accountNumber the unique account number to search for.
     * @return an Optional containing the EStatusAccount if found, or empty if not found.
     */
    Optional<EStatusAccount> findStatusByAccountNumber(String accountNumber);

    /**
     * Checks if an account with the given account number exists in the database.
     *
     * @param accountNumber the unique account number to check.
     * @return true if an account with the given account number exists, false otherwise.
     */
    boolean existsByAccountNumber(String accountNumber);
}
