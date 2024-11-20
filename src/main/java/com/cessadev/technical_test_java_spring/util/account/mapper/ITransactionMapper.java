package com.cessadev.technical_test_java_spring.util.account.mapper;

import com.cessadev.technical_test_java_spring.model.AccountModel;
import com.cessadev.technical_test_java_spring.model.TransactionModel;
import com.cessadev.technical_test_java_spring.model.dto.TransactionHistoryDTOResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ITransactionMapper {

    @Mapping(target = "accountOrigin", source = "accountOrigin")
    @Mapping(target = "accountDestination", source = "accountDestination")
    TransactionHistoryDTOResponse toTransactionHistoryDTO(TransactionModel transaction);

    List<TransactionHistoryDTOResponse> toTransactionHistoryDTOList(List<TransactionModel> transactionModels);

    default String map(AccountModel accountModel) {
        return accountModel != null ? accountModel.getAccountNumber() : null;
    }
}
