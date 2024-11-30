package com.cessadev.technical_test_java_spring.model;

import com.cessadev.technical_test_java_spring.model.enums.ERoles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "role")
public class RoleModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private ERoles role;

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    RoleModel roleModel = (RoleModel) o;
    return Objects.equals(id, roleModel.id) && role == roleModel.role;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, role);
  }

  @Override
  public String toString() {
    return "RoleModel{" +
            "Id=" + id +
            ", role=" + role +
            '}';
  }
}
