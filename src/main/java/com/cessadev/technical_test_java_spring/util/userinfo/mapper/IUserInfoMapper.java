package com.cessadev.technical_test_java_spring.util.userinfo.mapper;

import com.cessadev.technical_test_java_spring.model.UserInfoModel;
import com.cessadev.technical_test_java_spring.model.dto.CreateUserInfoDTORequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUserInfoMapper {

  UserInfoModel toEntity(CreateUserInfoDTORequest userInfoDTORequest);
}
