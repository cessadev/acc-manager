package com.cessadev.technical_test_java_spring.service.implementation;

import com.cessadev.technical_test_java_spring.model.RoleModel;
import com.cessadev.technical_test_java_spring.model.UserModel;
import com.cessadev.technical_test_java_spring.model.dto.CreateUserDTORequest;
import com.cessadev.technical_test_java_spring.model.enums.ERoles;
import com.cessadev.technical_test_java_spring.persistence.dao.IRoleDAO;
import com.cessadev.technical_test_java_spring.persistence.dao.IUserDAO;
import com.cessadev.technical_test_java_spring.service.IUserService;
import com.cessadev.technical_test_java_spring.util.user.mapper.IUserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

  private final IUserDAO userDAO;
  private final IUserMapper userMapper;
  private final PasswordEncoder passwordEncoder;
  private final IRoleDAO roleDAO;

  public UserServiceImpl(IUserDAO userDAO, IUserMapper userMapper, PasswordEncoder passwordEncoder, IRoleDAO roleDAO) {
    this.userDAO = userDAO;
    this.userMapper = userMapper;
    this.passwordEncoder = passwordEncoder;
    this.roleDAO = roleDAO;
  }

  @Override
  public void createUser(CreateUserDTORequest createUserDTORequest) {
    Set<RoleModel> roles = createUserDTORequest.roles().stream()
            .map(role -> roleDAO.findByRole(ERoles.valueOf(role))
                    .orElseThrow(() -> new RuntimeException("Role not found: " + role)))
            .collect(Collectors.toSet());

    UserModel userModel = userMapper.toEntity(createUserDTORequest);
    userModel.setRoles(roles);
    userModel.setPassword(passwordEncoder.encode(createUserDTORequest.password()));

    userDAO.createUser(userModel);
  }
}
