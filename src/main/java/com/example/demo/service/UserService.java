package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserService{
  
  private final UserRepository userRepository;

  @Autowired 
  public UserService(UserRepository userRepository){
    this.userRepository = userRepository;
  }
  
  public User postUser(User user){
    return userRepository.save(user);
  }
}