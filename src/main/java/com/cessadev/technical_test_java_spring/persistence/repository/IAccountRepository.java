package com.cessadev.technical_test_java_spring.persistence.repository;

import com.cessadev.technical_test_java_spring.model.AccountModel;
import com.cessadev.technical_test_java_spring.model.enums.EStatusAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * IAccountRepository extends JpaRepository to provide CRUD operations for AccountModel entities.
 * This interface includes additional methods for finding and verifying accounts by account number,
 * and for retrieving the status of an account. It enables seamless interaction with the database.
 */
@Repository
public interface IAccountRepository extends JpaRepository<AccountModel, UUID> {

    /**
     * Checks if an account with the given account number exists in the database.
     *
     * @param accountNumber the unique account number to check.
     * @return true if an account with the given account number exists, false otherwise.
     */
    boolean existsByAccountNumber(String accountNumber);

    /**
     * Finds an account by its unique account number.
     *
     * @param accountNumber the unique account number to search for.
     * @return the AccountModel associated with the given account number, or null if not found.
     */
    AccountModel findByAccountNumber(String accountNumber);

    /**
     * JPQL query
     * Retrieves the status of an account by its account number.
     *
     * @param accountNumber the unique account number to search for.
     * @return the EStatusAccount representing the status of the account, or null if the account is not found.
     */
    @Query("SELECT a.status FROM AccountModel a WHERE a.accountNumber = :accountNumber")
    EStatusAccount findStatusByAccountNumber(@Param("accountNumber") String accountNumber);
}
