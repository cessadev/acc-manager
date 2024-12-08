package com.cessadev.technical_test_java_spring.service.implementation;

import com.cessadev.technical_test_java_spring.exception.custom.account.ResourceNotFoundException;
import com.cessadev.technical_test_java_spring.model.UserInfoModel;
import com.cessadev.technical_test_java_spring.persistence.dao.IUserDAO;
import com.cessadev.technical_test_java_spring.persistence.dao.IUserInfoDAO;
import com.cessadev.technical_test_java_spring.service.IUserInfoService;
import com.cessadev.technical_test_java_spring.util.userinfo.mapper.IUserInfoMapper;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements IUserInfoService {

  private final IUserInfoDAO userInfoDAO;
  private final IUserDAO userDAO;

  public UserInfoServiceImpl(IUserInfoDAO userInfoDAO, IUserDAO userDAO, IUserInfoMapper userInfoMapper) {
    this.userInfoDAO = userInfoDAO;
    this.userDAO = userDAO;
  }

  @Override
  public UserInfoModel insertUserInfo(UserInfoModel userInfoModel) {
    boolean userExists = userDAO.existsById(userInfoModel.getUser().getId());

    if (userExists) {
      return userInfoDAO.insertUserInfo(userInfoModel);
    } else {
      throw new ResourceNotFoundException("Error inserting user information - user not exist: " + userInfoModel.getUser().getId());
    }
  }
}
