package com.cessadev.technical_test_java_spring.persistence.dao;

import com.cessadev.technical_test_java_spring.model.UserInfoModel;

import java.util.Optional;

public interface IUserInfoDAO {

  UserInfoModel insertUserInfo(UserInfoModel userInfoModel);

  Optional<UserInfoModel> findByUserId(Long userId);
}
