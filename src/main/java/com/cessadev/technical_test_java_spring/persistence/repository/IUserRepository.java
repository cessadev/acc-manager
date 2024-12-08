package com.cessadev.technical_test_java_spring.persistence.repository;

import com.cessadev.technical_test_java_spring.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<UserModel, Long> {

  UserModel findByEmail(String email);

  boolean existsById(Long userId);

  @Query("SELECT u FROM UserModel u JOIN FETCH u.userInfo WHERE u.id = :id")
  Optional<UserModel> findByIdWithUserInfo(@Param("id") Long id);
}
