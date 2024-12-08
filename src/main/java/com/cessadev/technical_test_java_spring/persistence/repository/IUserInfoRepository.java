package com.cessadev.technical_test_java_spring.persistence.repository;

import com.cessadev.technical_test_java_spring.model.UserInfoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserInfoRepository extends JpaRepository<UserInfoModel, Long> {

  UserInfoModel findByUserId(Long userId);
}
