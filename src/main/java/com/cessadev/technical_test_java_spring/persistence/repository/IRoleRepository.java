package com.cessadev.technical_test_java_spring.persistence.repository;

import com.cessadev.technical_test_java_spring.model.RoleModel;
import com.cessadev.technical_test_java_spring.model.enums.ERoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<RoleModel, Integer> {

  RoleModel findByRole(ERoles roles);
}
