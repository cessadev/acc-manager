package com.cessadev.technical_test_java_spring.controller.v1;

import com.cessadev.technical_test_java_spring.model.dto.CreateUserCompleteDTORequest;
import com.cessadev.technical_test_java_spring.model.dto.UserDTOResponse;
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
  public ResponseEntity<UserDTOResponse> createUser(@RequestBody @Validated CreateUserCompleteDTORequest userCompleteDTORequest) {
    try {
      UserDTOResponse userDTOResponse = userService.createUser(userCompleteDTORequest);
      return ResponseEntity.ok(userDTOResponse);
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }
}
