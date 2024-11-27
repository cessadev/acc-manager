package com.cessadev.technical_test_java_spring.persistence.extended.implementation;

import com.cessadev.technical_test_java_spring.model.TransactionModel;
import com.cessadev.technical_test_java_spring.model.enums.ETypeTransaction;
import com.cessadev.technical_test_java_spring.persistence.extended.TransactionExtRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TransactionExtRepositoryImpl implements TransactionExtRepository {

  @PersistenceContext
  private final EntityManager entityManager;

  @Override
  public List<TransactionModel> byFilter(String accountNumber, LocalDateTime startDate, LocalDateTime endDate, ETypeTransaction typeTransaction) {
    String queryBuilder = "SELECT t FROM TransactionModel t " +
            "LEFT JOIN t.accountOrigin ao " +
            "LEFT JOIN t.accountDestination ad " +
            "WHERE (:accountNumber IS NULL OR " +
            "       (ao.accountNumber = :accountNumber OR ad.accountNumber = :accountNumber)) " +
            "AND (:startDate IS NULL OR t.date >= :startDate) " +
            "AND (:endDate IS NULL OR t.date <= :endDate) " +
            "AND (:typeTransaction IS NULL OR t.typeTransaction = :typeTransaction)";

    TypedQuery<TransactionModel> query = entityManager.createQuery(queryBuilder, TransactionModel.class);

    query.setParameter("accountNumber", accountNumber);
    query.setParameter("startDate", startDate);
    query.setParameter("endDate", endDate);
    query.setParameter("typeTransaction", typeTransaction);

    return query.getResultList();
  }
}
