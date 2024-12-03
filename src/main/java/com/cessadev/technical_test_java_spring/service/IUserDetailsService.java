package com.cessadev.technical_test_java_spring.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface IUserDetailsService {

  UserDetails loadUserByUsername(String email);
}
