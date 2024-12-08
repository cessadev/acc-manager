package com.cessadev.technical_test_java_spring.service;

import com.cessadev.technical_test_java_spring.model.UserInfoModel;
import com.cessadev.technical_test_java_spring.model.dto.CreateUserInfoDTORequest;

import java.util.Optional;

public interface IUserInfoService {

  UserInfoModel insertUserInfo(UserInfoModel userInfoModel);
}
