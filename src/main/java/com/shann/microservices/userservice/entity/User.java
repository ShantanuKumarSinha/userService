package com.shann.microservices.userservice.entity;

import com.shann.microservices.userservice.dto.SignUpRequestDTO;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseModel {
  private String firstName;
  private String lastName;
  private String email;
  private String password;

  public static User from(SignUpRequestDTO signUpRequestDTO) {
    return User.builder()
        .firstName(signUpRequestDTO.firstName())
        .lastName(signUpRequestDTO.lastName())
        .email(signUpRequestDTO.email())
        .password(signUpRequestDTO.password())
        .build();
  }
}
