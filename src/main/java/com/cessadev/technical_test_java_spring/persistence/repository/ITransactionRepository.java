package com.cessadev.technical_test_java_spring.persistence.repository;

import com.cessadev.technical_test_java_spring.model.TransactionModel;
import com.cessadev.technical_test_java_spring.persistence.extended.TransactionExtRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * ITransactionRepository provides the data access layer for managing TransactionModel entities.
 * It extends JpaRepository, inheriting common CRUD operations, and defines custom queries
 * for advanced filtering of transactions.
 */
@Repository
public interface ITransactionRepository extends JpaRepository<TransactionModel, UUID>, TransactionExtRepository {
}
