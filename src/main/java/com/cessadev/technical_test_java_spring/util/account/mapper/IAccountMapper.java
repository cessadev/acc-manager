package com.cessadev.technical_test_java_spring.util.account.mapper;

import com.cessadev.technical_test_java_spring.model.AccountModel;
import com.cessadev.technical_test_java_spring.model.TransactionModel;
import com.cessadev.technical_test_java_spring.model.dto.AccountDTOResponse;
import com.cessadev.technical_test_java_spring.model.dto.CreateAccountDTORequest;
import com.cessadev.technical_test_java_spring.model.dto.TransactionHistoryDTOResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IAccountMapper {

    AccountDTOResponse toDTO(AccountModel accountModel);

    @Mapping(target = "accountNumber", ignore = true)
    @Mapping(target = "status", expression = "java(com.cessadev.technical_test_java_spring.model.enums.EStatusAccount.ACTIVE)")
    AccountModel toEntity(CreateAccountDTORequest createAccountDTORequest);
}
