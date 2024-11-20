package com.cessadev.technical_test_java_spring.persistence.dao.implementation;

import com.cessadev.technical_test_java_spring.model.TransactionModel;
import com.cessadev.technical_test_java_spring.model.enums.ETypeTransaction;
import com.cessadev.technical_test_java_spring.persistence.dao.ITransactionDAO;
import com.cessadev.technical_test_java_spring.persistence.repository.ITransactionRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class TransactionDAOImpl implements ITransactionDAO {

    private final ITransactionRepository transactionRepository;

    public TransactionDAOImpl(ITransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void insertTransaction(TransactionModel transactionModel) {
        transactionRepository.save(transactionModel);
    }

    @Override
    public List<TransactionModel> findByFilters(String accountNumber, LocalDateTime startDate, LocalDateTime endDate, ETypeTransaction typeTransaction) {
        return transactionRepository.findByFilters(accountNumber, startDate, endDate, typeTransaction);
    }
}
