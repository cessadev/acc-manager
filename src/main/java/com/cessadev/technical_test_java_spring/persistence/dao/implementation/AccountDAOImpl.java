package com.cessadev.technical_test_java_spring.persistence.dao.implementation;

import com.cessadev.technical_test_java_spring.persistence.dao.IAccountDAO;
import com.cessadev.technical_test_java_spring.persistence.repository.IAccountRepository;
import org.springframework.stereotype.Component;

@Component
public class AccountDAOImpl implements IAccountDAO {

    private final IAccountRepository accountRepository;

    public AccountDAOImpl(IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
}
