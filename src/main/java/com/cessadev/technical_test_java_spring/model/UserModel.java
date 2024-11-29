package com.cessadev.technical_test_java_spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String email;

  @JsonIgnore
  @Column(nullable = false)
  private String password;

  private boolean enabled;
  private boolean accountNonExpired;
  private boolean credentialsNonExpired;
  private boolean accountNonLocked;

  @ManyToMany(fetch = FetchType.EAGER, targetEntity = RoleModel.class, cascade = CascadeType.PERSIST)
  @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<RoleModel> roles;

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    UserModel userModel = (UserModel) o;
    return enabled == userModel.enabled &&
            accountNonExpired == userModel.accountNonExpired &&
            credentialsNonExpired == userModel.credentialsNonExpired &&
            accountNonLocked == userModel.accountNonLocked &&
            Objects.equals(id, userModel.id) &&
            Objects.equals(email, userModel.email) &&
            Objects.equals(password, userModel.password) &&
            Objects.equals(roles, userModel.roles);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
            id,
            email,
            password,
            enabled,
            accountNonExpired,
            credentialsNonExpired,
            accountNonLocked,
            roles);
  }

  @Override
  public String toString() {
    return "UserModel{" +
            "id=" + id +
            ", email='" + email + '\'' +
            ", enabled=" + enabled +
            ", accountNonExpired=" + accountNonExpired +
            ", credentialsNonExpired=" + credentialsNonExpired +
            ", accountNonLocked=" + accountNonLocked +
            ", roles=" + roles +
            '}';
  }
}
