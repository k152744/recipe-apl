package com.example.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;

import org.springframework.data.annotation.CreatedDate;
import java.time.LocalDateTime;

import java.util.List;

@Entity
@Table(name= "user")
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
  
  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public LocalDateTime getCreated() {
    return this.created;
  }

  public void setCreated(LocalDateTime created) {
    this.created = created;
  }
  
  
  public List<Recipe> getRecipeList() {
    return this.recipeList;
  }

  public void setRecipeList(List<Recipe> recipeList) {
    this.recipeList = recipeList;
  }
  
}