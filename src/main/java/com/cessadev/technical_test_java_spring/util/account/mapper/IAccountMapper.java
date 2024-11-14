package com.cessadev.technical_test_java_spring.util.account.mapper;

import com.cessadev.technical_test_java_spring.model.AccountModel;
import com.cessadev.technical_test_java_spring.model.dto.AccountDTOResponse;
import com.cessadev.technical_test_java_spring.model.dto.CreateAccountDTORequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IAccountMapper {

    AccountDTOResponse toDTO(AccountModel accountModel);

    @Mapping(target = "accountNumber", ignore = true)
    @Mapping(target = "balance", expression = "java(java.math.BigDecimal.ZERO)")
    @Mapping(target = "status", expression = "java(com.cessadev.technical_test_java_spring.model.enums.EStatusAccount.ACTIVE)")
    AccountModel toEntity(CreateAccountDTORequest createAccountDTORequest);
}
