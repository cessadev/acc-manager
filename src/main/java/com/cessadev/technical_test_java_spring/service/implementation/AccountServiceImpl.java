package com.cessadev.technical_test_java_spring.service.implementation;

import com.cessadev.technical_test_java_spring.model.AccountModel;
import com.cessadev.technical_test_java_spring.model.dto.*;
import com.cessadev.technical_test_java_spring.model.enums.EStatusAccount;
import com.cessadev.technical_test_java_spring.persistence.dao.IAccountDAO;
import com.cessadev.technical_test_java_spring.service.IAccountService;
import com.cessadev.technical_test_java_spring.util.mapper.IAccountMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        } while (accountDAO.existsByAccountNumberDAO(accountNumber));

        accountModel.setAccountNumber(accountNumber);

        accountDAO.createAccountDAO(accountModel);
    }

    @Override
    public UpdateAccountDTOResponse updateAccount(String accountNumber, UpdateAccountDTORequest request) {
        Optional<AccountModel> accountExist = accountDAO.findByAccountNumberDAO(accountNumber);

        if (accountExist.isEmpty()) {
            return new UpdateAccountDTOResponse("Account Not Found", null);
        }

        AccountModel accountModel = accountExist.get();

        if (request.ownerName() != null && request.status() != null) {
            accountModel.setOwnerName(request.ownerName());
            accountModel.setStatus(request.status());
        }

        AccountModel accountUpdated = accountDAO.createAccountWithReturnDAO(accountModel);
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

    public String generateAccountNumber() {
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 16);
        return uuid.substring(0, 4) + "-" + uuid.substring(4, 8) + "-" +
                uuid.substring(8, 12) + "-" + uuid.substring(12);
    }
}
