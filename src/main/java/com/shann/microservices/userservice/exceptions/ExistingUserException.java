package com.shann.microservices.userservice.exceptions;

public class ExistingUserException extends Exception {
  public ExistingUserException(String message) {
    super("User Already Exists. Please try out these: " + message);
  }
}
