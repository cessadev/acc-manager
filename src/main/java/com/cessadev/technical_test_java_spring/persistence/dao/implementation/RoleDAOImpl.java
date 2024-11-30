package com.cessadev.technical_test_java_spring.persistence.dao.implementation;

import com.cessadev.technical_test_java_spring.model.RoleModel;
import com.cessadev.technical_test_java_spring.model.enums.ERoles;
import com.cessadev.technical_test_java_spring.persistence.dao.IRoleDAO;
import com.cessadev.technical_test_java_spring.persistence.repository.IRoleRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RoleDAOImpl implements IRoleDAO {

  private final IRoleRepository roleRepository;

  public RoleDAOImpl(IRoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  @Override
  public Optional<RoleModel> findByRole(ERoles roles) {
    RoleModel roleModel = roleRepository.findByRole(roles);
    return Optional.ofNullable(roleModel);
  }
}
