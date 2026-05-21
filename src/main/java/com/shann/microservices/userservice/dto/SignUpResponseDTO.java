package com.shann.microservices.userservice.dto;

import com.shann.microservices.userservice.enums.ResponseStatus;

public record SignUpResponseDTO(ResponseStatus responseStatus, String message) {}
