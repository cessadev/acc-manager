package com.cessadev.technical_test_java_spring.persistence.dao.implementation;

import com.cessadev.technical_test_java_spring.model.UserModel;
import com.cessadev.technical_test_java_spring.persistence.dao.IUserDAO;
import com.cessadev.technical_test_java_spring.persistence.repository.IUserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDAOImpl implements IUserDAO {

  private final IUserRepository userRepository;

  public UserDAOImpl(IUserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public Optional<UserModel> findById(Long userId) {
    return userRepository.findById(userId);
  }

  @Override
  public Optional<UserModel> findByEmail(String email) {
    UserModel userModel = userRepository.findByEmail(email);
    return Optional.ofNullable(userModel);
  }

  @Override
  public UserModel createUser(UserModel userModel) {
    return userRepository.save(userModel);
  }

  @Override
  public boolean existsById(Long userId) {
    return userRepository.existsById(userId);
  }

  @Override
  public Optional<UserModel> findByIdWithUserInfo(Long id) {
    return userRepository.findByIdWithUserInfo(id);
  }
}
