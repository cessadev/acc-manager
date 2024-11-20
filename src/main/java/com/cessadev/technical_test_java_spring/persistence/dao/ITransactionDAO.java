package com.cessadev.technical_test_java_spring.persistence.dao;

import com.cessadev.technical_test_java_spring.model.TransactionModel;
import com.cessadev.technical_test_java_spring.model.enums.ETypeTransaction;

import java.time.LocalDateTime;
import java.util.List;

public interface ITransactionDAO {

    void insertTransaction(TransactionModel transactionModel);

    List<TransactionModel> findByFilters(String accountNumber, LocalDateTime startDate, LocalDateTime endDate, ETypeTransaction typeTransaction);
}
