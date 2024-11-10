package com.cessadev.technical_test_java_spring.persistence.dao;

import com.cessadev.technical_test_java_spring.model.AccountModel;

import java.util.List;
import java.util.Optional;

public interface IAccountDAO {

    List<AccountModel> findAllAccountsDAO();

    void createAccountDAO(AccountModel accountModel);

    AccountModel createAccountWithReturnDAO(AccountModel accountModel);

    Optional<AccountModel> findByAccountNumberDAO(String accountNumber);

    boolean existsByAccountNumberDAO(String accountNumber);
}
