package com.cessadev.technical_test_java_spring.persistence.dao.implementation;

import com.cessadev.technical_test_java_spring.model.AccountModel;
import com.cessadev.technical_test_java_spring.model.enums.EStatusAccount;
import com.cessadev.technical_test_java_spring.persistence.dao.IAccountDAO;
import com.cessadev.technical_test_java_spring.persistence.repository.IAccountRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * AccountDAOImpl is the implementation of the IAccountDAO interface, providing
 * data access operations for the AccountModel entities. This class is a component
 * that interacts with the database through the injected IAccountRepository.
 *
 * Responsibilities include:
 * - Fetching all accounts.
 * - Creating new accounts.
 * - Finding and verifying accounts by account number.
 * - Retrieving the status of an account by account number.
 *
 * This class is injected into the service layer to separate the data access logic.
 */
@Component
public class AccountDAOImpl implements IAccountDAO {

    private final IAccountRepository accountRepository;

    /**
     * Constructor for AccountDAOImpl.
     *
     * @param accountRepository the repository used for database operations.
     */
    public AccountDAOImpl(IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * Fetches all accounts from the database.
     *
     * @return a list of all AccountModel entities stored in the database.
     */
    @Override
    public List<AccountModel> findAllAccounts() {
        return accountRepository.findAll();
    }

    /**
     * Saves a new account in the database.
     *
     * @param accountModel the account model to be saved.
     */
    @Override
    public void createAccount(AccountModel accountModel) {
        accountRepository.save(accountModel);
    }

    /**
     * Update an account in the database without return.
     *
     * @param accountModel the account model to be saved.
     */
    @Override
    public void updateAccount(AccountModel accountModel) {
        accountRepository.save(accountModel);
    }

    /**
     * Update an account in the database and returns the updated entity.
     *
     * @param accountModel the account model to be saved.
     * @return the saved AccountModel entity.
     */
    @Override
    public AccountModel updateAccountWithReturn(AccountModel accountModel) {
        return accountRepository.save(accountModel);
    }

    /**
     * Finds an account by its account number.
     *
     * @param accountNumber the unique account number to search for.
     * @return an Optional containing the AccountModel if found, or empty if not found.
     */
    @Override
    public Optional<AccountModel> findByAccountNumber(String accountNumber) {
        AccountModel accountModel = accountRepository.findByAccountNumber(accountNumber);
        return Optional.ofNullable(accountModel);
    }

    /**
     * Retrieves the status of an account by its account number.
     *
     * @param accountNumber the unique account number to search for.
     * @return an Optional containing the EStatusAccount if found, or empty if not found.
     */
    @Override
    public Optional<EStatusAccount> findStatusByAccountNumber(String accountNumber) {
        EStatusAccount statusAccount = accountRepository.findStatusByAccountNumber(accountNumber);
        return Optional.ofNullable(statusAccount);
    }

    /**
     * Checks if an account exists by its account number.
     *
     * @param accountNumber the unique account number to check.
     * @return true if an account with the given account number exists, false otherwise.
     */
    @Override
    public boolean existsByAccountNumber(String accountNumber) {
        return accountRepository.existsByAccountNumber(accountNumber);
    }
}
