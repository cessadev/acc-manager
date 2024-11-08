package com.cessadev.technical_test_java_spring.persistence.dao.implementation;

import com.cessadev.technical_test_java_spring.model.AccountModel;
import com.cessadev.technical_test_java_spring.persistence.dao.IAccountDAO;
import com.cessadev.technical_test_java_spring.persistence.repository.IAccountRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountDAOImpl implements IAccountDAO {

    private final IAccountRepository accountRepository;

    public AccountDAOImpl(IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<AccountModel> findAllAccountsDAO() {
        return accountRepository.findAll();
    }

    @Override
    public void createAccountDAO(AccountModel accountModel) {
        accountRepository.save(accountModel);
    }

    @Override
    public boolean existsByAccountNumber(String accountNumber) {
        return accountRepository.existsByAccountNumber(accountNumber);
    }
}
