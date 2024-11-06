package com.cessadev.technical_test_java_spring.util.mapper;

import com.cessadev.technical_test_java_spring.model.AccountModel;
import com.cessadev.technical_test_java_spring.model.dto.AccountDTOResponse;
import com.cessadev.technical_test_java_spring.model.dto.CreateAccountDTORequest;

public interface IAccountMapper {

    AccountDTOResponse toDTO(AccountModel accountModel);

    AccountModel toEntity(CreateAccountDTORequest createAccountDTORequest);
}
