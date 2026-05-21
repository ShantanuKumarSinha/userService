package com.shann.microservices.userservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Token extends BaseModel {

  private String tokenValue;
  private LocalTime expiryTime;
  @OneToOne
  @JoinColumn(name = "userId", referencedColumnName = "id")
  private User user;

}
