package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class UserService{
  
  private final UserRepository userRepository;

  @Autowired 
  public UserService(UserRepository userRepository){
    this.userRepository = userRepository;
  }
  
  public User postUser(String name,String email,String password){
    User user = new User();

    user.setName(name);
    user.setEmail(email);
    user.setPassword(password);
    user.setCreated(LocalDateTime.now());

    return userRepository.save(user);
  }
}