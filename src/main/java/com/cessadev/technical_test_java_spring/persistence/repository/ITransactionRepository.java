package com.cessadev.technical_test_java_spring.persistence.repository;

import com.cessadev.technical_test_java_spring.model.TransactionModel;
import com.cessadev.technical_test_java_spring.model.enums.ETypeTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ITransactionRepository extends JpaRepository<TransactionModel, UUID> {

    @Query("SELECT t FROM TransactionModel t " +
            "LEFT JOIN t.accountOrigin ao " +
            "LEFT JOIN t.accountDestination ad " +
            "WHERE (:accountNumber IS NULL OR " +
            "       (ao.accountNumber = :accountNumber OR ad.accountNumber = :accountNumber)) " +
            "AND (:startDate IS NULL OR t.date >= :startDate) " +
            "AND (:endDate IS NULL OR t.date <= :endDate) " +
            "AND (:typeTransaction IS NULL OR t.typeTransaction = :typeTransaction)")
    List<TransactionModel> findByFilters(String accountNumber, LocalDateTime startDate, LocalDateTime endDate, ETypeTransaction typeTransaction);
}
