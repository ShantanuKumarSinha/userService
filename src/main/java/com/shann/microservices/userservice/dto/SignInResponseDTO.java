package com.shann.microservices.userservice.dto;

import com.shann.microservices.userservice.entity.Token;
import com.shann.microservices.userservice.enums.ResponseStatus;

public record SignInResponseDTO(Token token, ResponseStatus responseStatus) {}
