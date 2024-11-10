package com.cessadev.technical_test_java_spring.persistence.repository;

import com.cessadev.technical_test_java_spring.model.AccountModel;
import com.cessadev.technical_test_java_spring.model.enums.EStatusAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IAccountRepository extends JpaRepository<AccountModel, UUID> {

    boolean existsByAccountNumber(String accountNumber);

    AccountModel findByAccountNumber(String accountNumber);

    @Query("SELECT a.status FROM AccountModel a WHERE a.accountNumber = :accountNumber")
    EStatusAccount findStatusByAccountNumber(@Param("accountNumber") String accountNumber);
}
