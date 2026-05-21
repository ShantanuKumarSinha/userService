package com.shann.microservices.userservice.service.impl;

import com.shann.microservices.userservice.entity.Token;
import com.shann.microservices.userservice.entity.User;
import com.shann.microservices.userservice.exceptions.ExistingUserException;
import com.shann.microservices.userservice.exceptions.UserNotFoundException;
import com.shann.microservices.userservice.repository.TokenRepository;
import com.shann.microservices.userservice.repository.UserRepository;
import com.shann.microservices.userservice.service.UserService;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalTime;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;

  public UserServiceImpl(
      UserRepository userRepository,
      TokenRepository tokenRepository,
      PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.tokenRepository = tokenRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public void signUp(User user) throws ExistingUserException {
    if (userRepository.findByEmail(user.getEmail()).isPresent())
      throw new ExistingUserException("Someone@random.com");
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepository.save(user);
  }

  @Override
  public Token signIn(String email, String password)
      throws UserNotFoundException, NoSuchAlgorithmException {
    var user =
        userRepository
            .findByEmail(email)
            .orElseThrow(
                () -> new UserNotFoundException("User with email " + email + " not found"));
    var token =
        tokenRepository
            .findByUserId(user.getId())
            .orElse(
                Token.builder()
                    .tokenValue(String.valueOf(SecureRandom.getInstance("SHA1PRNG")))
                    .user(user)
                    .build());
    token.setExpiryTime(LocalTime.now().plusMinutes(5));
    return tokenRepository.save(token);
  }
}
