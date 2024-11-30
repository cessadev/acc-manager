package com.cessadev.technical_test_java_spring.util.user.mapper;

import com.cessadev.technical_test_java_spring.model.UserModel;
import com.cessadev.technical_test_java_spring.model.dto.CreateUserDTORequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IUserMapper {

//  @Mapping(target = "enabled", ignore = true)
//  @Mapping(target = "accountNonExpired", ignore = true)
//  @Mapping(target = "credentialsNonExpired", ignore = true)
//  @Mapping(target = "accountNonLocked", ignore = true)
  @Mapping(target = "password", ignore = true)
  @Mapping(target = "roles", ignore = true)
  UserModel toEntity(CreateUserDTORequest createUserDTORequest);
}
