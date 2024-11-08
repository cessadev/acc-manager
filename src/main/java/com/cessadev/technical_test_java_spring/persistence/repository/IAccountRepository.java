package com.cessadev.technical_test_java_spring.persistence.repository;

import com.cessadev.technical_test_java_spring.model.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IAccountRepository extends JpaRepository<AccountModel, UUID> {

    boolean existsByAccountNumber(String accountNumber);
}
