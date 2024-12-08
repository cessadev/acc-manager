package com.cessadev.technical_test_java_spring.persistence.dao.implementation;

import com.cessadev.technical_test_java_spring.model.UserInfoModel;
import com.cessadev.technical_test_java_spring.persistence.dao.IUserInfoDAO;
import com.cessadev.technical_test_java_spring.persistence.repository.IUserInfoRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoDAOImpl implements IUserInfoDAO {

  private final IUserInfoRepository userInfoRepository;

  public UserInfoDAOImpl(IUserInfoRepository userInfoRepository) {
    this.userInfoRepository = userInfoRepository;
  }

  @Override
  public UserInfoModel insertUserInfo(UserInfoModel userInfoModel) {
    return userInfoRepository.save(userInfoModel);
  }

  @Override
  public Optional<UserInfoModel> findByUserId(Long userId) {
    UserInfoModel userInfoModel = userInfoRepository.findByUserId(userId);
    return Optional.ofNullable(userInfoModel);
  }
}
