package com.cessadev.technical_test_java_spring.service.implementation;

import com.cessadev.technical_test_java_spring.model.dto.*;
import com.cessadev.technical_test_java_spring.service.IAccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AccountServiceImpl implements IAccountService {

    public String generateAccountNumber() {
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 16);
        return uuid.substring(0, 4) + "-" + uuid.substring(4, 8) + "-" +
                uuid.substring(8, 12) + "-" + uuid.substring(12);
    }

    @Override
    public List<AccountDTOResponse> findAllAccounts() {
        return null;
    }

    @Override
    public void createAccount(CreateAccountDTORequest accountDTORequest) {

    }

    @Override
    public UpdateAccountDTOResponse updateAccount(UpdateAccountDTORequest updateAccountDTORequest) {
        return null;
    }

    @Override
    public StatusAccountDTOResponse updateStatusAccount(String accountNumber) {
        return null;
    }
}
