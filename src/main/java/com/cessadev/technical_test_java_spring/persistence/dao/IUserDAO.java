package com.cessadev.technical_test_java_spring.persistence.dao;

import com.cessadev.technical_test_java_spring.model.UserModel;

import java.util.Optional;

public interface IUserDAO {

  Optional<UserModel> findByEmail(String email);

  void createUser(UserModel userModel);
}
