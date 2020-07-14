package com.example.demo.entity;

import lombok.Data;

@Data
public class UpdateUser{

  private int id;
  private String name;
  private String email;
  private String password;

}