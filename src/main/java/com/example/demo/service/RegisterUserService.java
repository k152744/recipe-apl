package com.example.demo.service;

import com.example.demo.entity.RegistrationUser;
import com.example.demo.entity.Recipe;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class RegisterUserService{
  
  private final UserRepository userRepository;

  @Autowired
  PasswordEncoder passwordEncoder;
  
  @Autowired 
  public RegisterUserService(UserRepository userRepository){
    this.userRepository = userRepository;
  }
  
  public RegistrationUser postUser(RegistrationUser user){
    
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setCreated(LocalDateTime.now());

    return userRepository.save(user);
  }

  public RegistrationUser updateUser(RegistrationUser user){
    return userRepository.save(user);

  }

  public void deleteUser(RegistrationUser user){
    userRepository.delete(user);
  }
}