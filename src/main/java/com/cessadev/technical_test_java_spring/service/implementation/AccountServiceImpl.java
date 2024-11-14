package com.cessadev.technical_test_java_spring.service.implementation;

import com.cessadev.technical_test_java_spring.model.AccountModel;
import com.cessadev.technical_test_java_spring.model.dto.*;
import com.cessadev.technical_test_java_spring.model.enums.EStatusAccount;
import com.cessadev.technical_test_java_spring.persistence.dao.IAccountDAO;
import com.cessadev.technical_test_java_spring.service.IAccountService;
import com.cessadev.technical_test_java_spring.util.account.AccountNumberGenerator;
import com.cessadev.technical_test_java_spring.util.account.mapper.IAccountMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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
        List<AccountModel> accountModelList = accountDAO.findAllAccounts();
        return accountModelList.stream().map(
                accountMapper::toDTO
        ).toList();
    }

    @Override
    public void createAccount(CreateAccountDTORequest accountDTORequest) {
        AccountModel accountModel = accountMapper.toEntity(accountDTORequest);

        String accountNumber;
        do {
            accountNumber = AccountNumberGenerator.generateAccountNumber();
        } while (accountDAO.existsByAccountNumber(accountNumber));

        accountModel.setAccountNumber(accountNumber);

        if (accountModel.getBalance().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }

        accountDAO.createAccount(accountModel);
    }

    @Override
    public UpdateAccountDTOResponse updateAccount(String accountNumber, UpdateAccountDTORequest request) {
        Optional<AccountModel> accountExist = accountDAO.findByAccountNumber(accountNumber);

        if (accountExist.isEmpty()) {
            return new UpdateAccountDTOResponse("Account Not Found", null);
        }

        AccountModel accountModel = accountExist.get();

        if (request.ownerName() != null && request.status() != null) {
            accountModel.setOwnerName(request.ownerName());
            accountModel.setStatus(request.status());
        }

        AccountModel accountUpdated = accountDAO.createAccountWithReturn(accountModel);
        AccountDTOResponse accountDTOResponse = accountMapper.toDTO(accountUpdated);

        return new UpdateAccountDTOResponse("Account updated successfully", accountDTOResponse);
    }

    @Override
    public StatusAccountDTOResponse findStatusAccount(String accountNumber) {
        Optional<EStatusAccount> statusAccount = accountDAO.findStatusByAccountNumber(accountNumber);

        if (statusAccount.isEmpty()) {
            return new StatusAccountDTOResponse("Account Not Exist", accountNumber, null);
        }

        EStatusAccount status = statusAccount.get();
        return new StatusAccountDTOResponse("Current account status", accountNumber, status);
    }
}
