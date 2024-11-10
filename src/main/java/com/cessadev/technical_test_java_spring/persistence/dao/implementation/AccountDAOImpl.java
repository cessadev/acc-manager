package com.cessadev.technical_test_java_spring.persistence.dao.implementation;

import com.cessadev.technical_test_java_spring.model.AccountModel;
import com.cessadev.technical_test_java_spring.model.enums.EStatusAccount;
import com.cessadev.technical_test_java_spring.persistence.dao.IAccountDAO;
import com.cessadev.technical_test_java_spring.persistence.repository.IAccountRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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
    public AccountModel createAccountWithReturnDAO(AccountModel accountModel) {
        return accountRepository.save(accountModel);
    }

    @Override
    public Optional<AccountModel> findByAccountNumberDAO(String accountNumber) {
        AccountModel accountModel = accountRepository.findByAccountNumber(accountNumber);
        return Optional.ofNullable(accountModel);
    }

    @Override
    public Optional<EStatusAccount> findStatusByAccountNumber(String accountNumber) {
        EStatusAccount statusAccount = accountRepository.findStatusByAccountNumber(accountNumber);
        return Optional.ofNullable(statusAccount);
    }

    @Override
    public boolean existsByAccountNumberDAO(String accountNumber) {
        return accountRepository.existsByAccountNumber(accountNumber);
    }
}
