package com.cessadev.technical_test_java_spring.persistence.dao;

import com.cessadev.technical_test_java_spring.model.AccountModel;

import java.util.List;

public interface IAccountDAO {

    List<AccountModel> findAllAccountsDAO();

    void createAccountDAO(AccountModel accountModel);
}
