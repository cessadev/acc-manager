package com.cessadev.technical_test_java_spring.service;

import com.cessadev.technical_test_java_spring.model.UserModel;
import com.cessadev.technical_test_java_spring.model.dto.CreateUserCompleteDTORequest;
import com.cessadev.technical_test_java_spring.model.dto.UserDTOResponse;
import com.cessadev.technical_test_java_spring.model.dto.UserWithInfoDTOResponse;

import java.util.Optional;

public interface IUserService {

  Optional<UserModel> findById(Long userId);

  UserDTOResponse createUser(CreateUserCompleteDTORequest userCompleteDTORequest);

  UserWithInfoDTOResponse getUserWithInfo(Long id);
}
