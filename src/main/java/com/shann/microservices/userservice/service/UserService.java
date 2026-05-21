package com.shann.microservices.userservice.service;

import com.shann.microservices.userservice.entity.Token;
import com.shann.microservices.userservice.entity.User;
import com.shann.microservices.userservice.exceptions.ExistingUserException;
import com.shann.microservices.userservice.exceptions.UserNotFoundException;

import java.security.NoSuchAlgorithmException;

public interface UserService {
    void signUp(User user) throws ExistingUserException;
    Token signIn(String email, String password) throws UserNotFoundException, NoSuchAlgorithmException;
}
