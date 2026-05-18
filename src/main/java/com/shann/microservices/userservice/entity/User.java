package com.shann.microservices.userservice.entity;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class User extends BaseModel {
    private String name;
    private String email;
}
