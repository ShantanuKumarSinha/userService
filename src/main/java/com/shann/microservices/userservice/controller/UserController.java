package com.shann.microservices.userservice.controller;

import com.shann.microservices.userservice.dto.SignInRequestDTO;
import com.shann.microservices.userservice.dto.SignUpRequestDTO;
import com.shann.microservices.userservice.dto.SignUpResponseDTO;
import com.shann.microservices.userservice.entity.User;
import com.shann.microservices.userservice.enums.ResponseStatus;
import com.shann.microservices.userservice.exceptions.ExistingUserException;
import com.shann.microservices.userservice.exceptions.UserNotFoundException;
import com.shann.microservices.userservice.service.UserService;
import java.security.NoSuchAlgorithmException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/hello")
  public String hello() {
    return "Hello from User Service!";
  }

  @PostMapping("/signUp")
  public SignUpResponseDTO signUp(@RequestBody SignUpRequestDTO singUpRequestDTO) {
    try {
      var user = User.from(singUpRequestDTO);
      userService.signUp(user);
      return new SignUpResponseDTO(ResponseStatus.SUCCESS, "User created");
    } catch (ExistingUserException e) {
      return new SignUpResponseDTO(ResponseStatus.FAILURE, e.getMessage());
    }
  }

  @PostMapping("/signIn")
  public SignUpResponseDTO signIn(@RequestBody SignInRequestDTO signInRequestDTO) {
    try {
      var token = userService.signIn(signInRequestDTO.email(), signInRequestDTO.password());
      return new SignUpResponseDTO(ResponseStatus.SUCCESS, token.getTokenValue());
    } catch (UserNotFoundException | NoSuchAlgorithmException e) {
      return new SignUpResponseDTO(ResponseStatus.FAILURE, null);
    }
  }
}
