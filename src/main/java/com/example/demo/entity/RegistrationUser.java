package com.example.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.CreatedDate;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name= "user")
@Data
public class RegistrationUser{
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private int id;
  private String name;
  private String email;
  private String password;

  @CreatedDate
  private LocalDateTime created;
  
  @OneToMany(cascade = CascadeType.ALL,mappedBy = "registrationUser",orphanRemoval = true)
  private List<Recipe> recipeList;
  
  @OneToMany(cascade = CascadeType.ALL,mappedBy = "registrationUser",orphanRemoval = true)
  private List<Good> goodList;

}