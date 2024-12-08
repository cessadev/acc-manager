package com.cessadev.technical_test_java_spring.persistence.dao;

import com.cessadev.technical_test_java_spring.model.UserModel;

import java.util.Optional;

public interface IUserDAO {

  Optional<UserModel> findById(Long userId);

  Optional<UserModel> findByEmail(String email);

  UserModel createUser(UserModel userModel);

  boolean existsById(Long userId);

  Optional<UserModel> findByIdWithUserInfo(Long id);
}
