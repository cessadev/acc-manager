package com.cessadev.technical_test_java_spring.config.security;

import com.cessadev.technical_test_java_spring.model.UserModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

  private final UserModel userModel;

  public CustomUserDetails(UserModel userModel) {
    this.userModel = userModel;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.emptyList();
  }

  @Override
  public String getPassword() {
    return userModel.getPassword();
  }

  @Override
  public String getUsername() {
    return userModel.getEmail();
  }

  @Override
  public boolean isAccountNonExpired() {
    return userModel.isAccountNonExpired();
  }

  @Override
  public boolean isAccountNonLocked() {
    return userModel.isAccountNonLocked();
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return userModel.isCredentialsNonExpired();
  }

  @Override
  public boolean isEnabled() {
    return userModel.isEnabled();
  }
}
