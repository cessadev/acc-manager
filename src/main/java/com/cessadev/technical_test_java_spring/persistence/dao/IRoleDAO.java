package com.cessadev.technical_test_java_spring.persistence.dao;

import com.cessadev.technical_test_java_spring.model.RoleModel;
import com.cessadev.technical_test_java_spring.model.enums.ERoles;

import java.util.Optional;

public interface IRoleDAO {

  Optional<RoleModel> findByRole(ERoles roles);
}
