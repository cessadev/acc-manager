package com.cessadev.technical_test_java_spring.service.implementation;

import com.cessadev.technical_test_java_spring.model.AccountModel;
import com.cessadev.technical_test_java_spring.model.dto.*;
import com.cessadev.technical_test_java_spring.persistence.dao.IAccountDAO;
import com.cessadev.technical_test_java_spring.service.IAccountService;
import com.cessadev.technical_test_java_spring.util.mapper.IAccountMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AccountServiceImpl implements IAccountService {

    private final IAccountDAO accountDAO;
    private final IAccountMapper accountMapper;

    public AccountServiceImpl(IAccountDAO accountDAO, IAccountMapper accountMapper) {
        this.accountDAO = accountDAO;
        this.accountMapper = accountMapper;
    }

    @Override
    public List<AccountDTOResponse> findAllAccounts() {
        List<AccountModel> accountModelList = accountDAO.findAllAccountsDAO();
        return accountModelList.stream().map(
                accountMapper.INSTANCE::toDTO
        ).toList();
    }

    @Override
    public void createAccount(CreateAccountDTORequest accountDTORequest) {
        AccountModel accountModel = accountMapper.INSTANCE.toEntity(accountDTORequest);

        String accountNumber;
        do {
            accountNumber = generateAccountNumber();
        } while (accountDAO.existsByAccountNumber(accountNumber));

        accountModel.setAccountNumber(accountNumber);

        accountDAO.createAccountDAO(accountModel);
    }

    @Override
    public UpdateAccountDTOResponse updateAccount(UpdateAccountDTORequest updateAccountDTORequest) {
        return null;
    }

    @Override
    public StatusAccountDTOResponse updateStatusAccount(String accountNumber) {
        return null;
    }

    public String generateAccountNumber() {
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 16);
        return uuid.substring(0, 4) + "-" + uuid.substring(4, 8) + "-" +
                uuid.substring(8, 12) + "-" + uuid.substring(12);
    }
}
