package com.cessadev.technical_test_java_spring.service.implementation;

import com.cessadev.technical_test_java_spring.exception.custom.account.ResourceNotFoundException;
import com.cessadev.technical_test_java_spring.model.RoleModel;
import com.cessadev.technical_test_java_spring.model.UserInfoModel;
import com.cessadev.technical_test_java_spring.model.UserModel;
import com.cessadev.technical_test_java_spring.model.dto.*;
import com.cessadev.technical_test_java_spring.model.enums.ERoles;
import com.cessadev.technical_test_java_spring.persistence.dao.IRoleDAO;
import com.cessadev.technical_test_java_spring.persistence.dao.IUserDAO;
import com.cessadev.technical_test_java_spring.persistence.dao.IUserInfoDAO;
import com.cessadev.technical_test_java_spring.service.IUserInfoService;
import com.cessadev.technical_test_java_spring.service.IUserService;
import com.cessadev.technical_test_java_spring.util.user.mapper.IUserMapper;
import com.cessadev.technical_test_java_spring.util.userinfo.mapper.IUserInfoMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

  private final IUserDAO userDAO;
  private final IUserMapper userMapper;
  private final PasswordEncoder passwordEncoder;
  private final IRoleDAO roleDAO;
  private final IUserInfoService userInfoService;
  private final IUserInfoMapper userInfoMapper;

  public UserServiceImpl(IUserDAO userDAO, IUserMapper userMapper, PasswordEncoder passwordEncoder, IRoleDAO roleDAO, IUserInfoService userInfoService, IUserInfoDAO userInfoDAO, IUserInfoMapper userInfoMapper) {
    this.userDAO = userDAO;
    this.userMapper = userMapper;
    this.passwordEncoder = passwordEncoder;
    this.roleDAO = roleDAO;
    this.userInfoService = userInfoService;
    this.userInfoMapper = userInfoMapper;
  }

  @Override
  public Optional<UserModel> findById(Long userId) {
    return userDAO.findById(userId);
  }

  @Override
  @Transactional
  public UserDTOResponse createUser(CreateUserCompleteDTORequest userCompleteDTORequest) {

    CreateUserDTORequest createUserDTORequest = new CreateUserDTORequest(
            userCompleteDTORequest.email(),
            userCompleteDTORequest.password(),
            userCompleteDTORequest.roles()
    );

    Set<RoleModel> roles = createUserDTORequest.roles().stream()
            .map(role -> roleDAO.findByRole(ERoles.valueOf(role))
                    .orElseThrow(() -> new RuntimeException("Role not found: " + role)))
            .collect(Collectors.toSet());

    UserModel userModelEntity = userMapper.toEntity(createUserDTORequest);
    userModelEntity.setRoles(roles);
    userModelEntity.setPassword(passwordEncoder.encode(createUserDTORequest.password()));

    UserModel userModelSaved = userDAO.createUser(userModelEntity);

    /* UserInfoModel */

    CreateUserInfoDTORequest userInfoDTORequest = new CreateUserInfoDTORequest(
            userCompleteDTORequest.firstName(),
            userCompleteDTORequest.lastName(),
            userCompleteDTORequest.middleName(),
            userCompleteDTORequest.dateOfBirth(),
            userCompleteDTORequest.gender(),
            userCompleteDTORequest.phoneNumber(),
            userCompleteDTORequest.alternatePhoneNumber(),
            userCompleteDTORequest.address(),
            userCompleteDTORequest.city(),
            userCompleteDTORequest.state(),
            userCompleteDTORequest.postalCode(),
            userCompleteDTORequest.country(),
            userCompleteDTORequest.employmentStatus(),
            userCompleteDTORequest.jobTitle(),
            userCompleteDTORequest.companyName(),
            userCompleteDTORequest.industry(),
            userCompleteDTORequest.annualIncome(),
            userCompleteDTORequest.creditScore(),
            userCompleteDTORequest.preferredCurrency(),
            userCompleteDTORequest.maritalStatus(),
            userCompleteDTORequest.numberOfDependents(),
            userCompleteDTORequest.profilePictureUrl(),
            null
    );

    UserInfoModel userInfoModel = userInfoMapper.toEntity(userInfoDTORequest);
    userInfoModel.setUser(userModelSaved);

    UserInfoModel userInfoModelSaved = userInfoService.insertUserInfo(userInfoModel);

    return new UserDTOResponse(userInfoModelSaved.getId(), userModelSaved.getEmail());
  }

  @Override
  public UserWithInfoDTOResponse getUserWithInfo(Long id) {
    Optional<UserModel> userModelOptional = userDAO.findByIdWithUserInfo(id);

    if (userModelOptional.isEmpty()) {
      throw new ResourceNotFoundException("User not found:" + id);
    }

    UserModel user = userModelOptional.get();

    UserInfoModel userInfo = user.getUserInfo();

    return new UserWithInfoDTOResponse(
            user.getId(),
            user.getEmail(),
            userInfo.getFirstName(),
            userInfo.getLastName(),
            userInfo.getAddress(),
            userInfo.getPhoneNumber(),
            userInfo.getCity()
    );
  }
}
