package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
//トランザクション処理をかける
@Transactional
public class UserService{
  @Autowired
  UserRepository userRepository;

  public User postUser(User user){
    return userRepository.save(user);
  }
}