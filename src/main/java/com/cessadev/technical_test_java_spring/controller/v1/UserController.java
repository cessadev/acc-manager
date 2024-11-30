package com.cessadev.technical_test_java_spring.controller.v1;

import com.cessadev.technical_test_java_spring.model.dto.CreateUserDTORequest;
import com.cessadev.technical_test_java_spring.service.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

  private final IUserService userService;

  public UserController(IUserService userService) {
    this.userService = userService;
  }

  @PostMapping("/create")
  public ResponseEntity<String> createUser(@RequestBody @Validated CreateUserDTORequest createUserDTORequest) {
    try {
      userService.createUser(createUserDTORequest);
      return ResponseEntity.ok("User created successfully");
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }
}
