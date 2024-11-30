package com.cessadev.technical_test_java_spring.service;

import com.cessadev.technical_test_java_spring.model.dto.CreateUserDTORequest;

public interface IUserService {

  void createUser(CreateUserDTORequest createUserDTORequest);
}
