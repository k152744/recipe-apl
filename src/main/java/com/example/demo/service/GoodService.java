package com.example.demo.service;

import com.example.demo.entity.Good;
import com.example.demo.repository.GoodRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class GoodService{

  @Autowired
  GoodRepository goodRepository;

  public Good postLike(Good like){
    return goodRepository.save(like);
  }
}
