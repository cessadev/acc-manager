package com.cessadev.technical_test_java_spring.service;

import com.cessadev.technical_test_java_spring.model.dto.*;

import java.util.List;

public interface IAccountService {

    List<AccountDTOResponse> findAllAccounts();

    void createAccount(CreateAccountDTORequest accountDTORequest);

    UpdateAccountDTOResponse updateAccount(String accountNumber, UpdateAccountDTORequest updateAccountDTORequest);

    StatusAccountDTOResponse findStatusAccount(String accountNumber);
}
